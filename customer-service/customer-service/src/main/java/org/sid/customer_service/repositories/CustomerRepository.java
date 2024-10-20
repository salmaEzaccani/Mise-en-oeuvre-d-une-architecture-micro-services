package org.sid.customer_service.repositories;

import org.sid.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//Dans votre code, vous avez une entité Customer et un dépôt CustomerRepository. Spring Data REST génère automatiquement un chemin REST pour cette entité. Par défaut, il prend le nom de la classe de l'entité (Customer) et le met au pluriel pour définir le chemin. Cela donne :Nom de l'entité : Customer
//Chemin par défaut généré : /customers
//CAD JE DEMANDE AU SPRING AU DEMARAGE DE DEMARER UN WS RESTFUL QUI PERMET DE GERER CUSTOMET AUTOMATICLY "POST GET.." SANS BESOIN DUN CONTROLLER
@RepositoryRestResource   //API RESTFUL
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
