package org.sid.billingservice.web;


import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path="/fullBill/{id}") //Dans ma facture jai id et jai product item et customer
    public Bill getBill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();//JE RECUPERE FACTURE A PARTIR DE BDD DE MICROSERVICE
        Customer customer=customerRestClient.getCustomerById(bill.getCustomerID());//JE RECUPERE CLIENT A PARRTIR DAN AUTRE MICROSERVICE VIA REST
        bill.setCustomer(customer);//je veux que lorsque jaffiche la factute quil aura aussi details de customer et product
        bill.getProductItems().forEach(pi ->{
            Product product=productItemRestClient.getProductById(pi.getProductID());
            //pi.setProduct(product);
            pi.setProductName(product.getName());
        });
        return bill;
    }
}
