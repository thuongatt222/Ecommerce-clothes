package ecom.clothes.repositories;

import ecom.clothes.model.ProductDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {
}
