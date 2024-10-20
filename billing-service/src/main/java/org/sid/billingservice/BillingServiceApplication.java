package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductItemRestClient productItemRestClient
            ){
         return args -> {

            //apartir de service de facture je veux recuperer liste des client
             Customer customer=customerRestClient.getCustomerById(1L);//je le recupere a partir dautre microservice et non ppas BDD
             Bill bill1= billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));//jajoute une facture a un cclient qui existe
             PagedModel<Product> productPagedModel=productItemRestClient.pageProducts();//recuperer les produits
             productPagedModel.forEach(p->{  //ajouter produit a une facture bill1
                 ProductItem productItem=new ProductItem();
                 productItem.setPrice(p.getPrice());
                 productItem.setQuantity(1+new Random().nextInt(100));
                 productItem.setBill(bill1);
                 productItem.setProductID(p.getId());
                 productItemRepository.save(productItem);
                 //jajoute a la facture un produit que je recupere a partir dun autre microservice
             });

         };
    }
}
