package org.sid.billingservice.repository;

import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
   public Collection<ProductItem> findByBillId(Long id);//je donne id de facture et me donne la liste des product dans cette facture
}
