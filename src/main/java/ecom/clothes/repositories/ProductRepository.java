package ecom.clothes.repositories;

import ecom.clothes.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE lower(p.title) LIKE :title")
    Page<ProductEntity> findByTitle(String title, Pageable pageable);

    ProductEntity findByTitleIs(String title);
}
