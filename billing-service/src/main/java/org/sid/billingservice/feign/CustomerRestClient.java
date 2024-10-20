package org.sid.billingservice.feign;

import org.sid.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//POUR recuperer liste des client a partir microservice CUSTOMER
@FeignClient(name="CUSTOMER-SERVICE") //permet de communiquer vec microservice via REST
public interface CustomerRestClient {
    @GetMapping(path="/customers/{id}")
     Customer getCustomerById(@PathVariable(name ="id") Long id);
}
