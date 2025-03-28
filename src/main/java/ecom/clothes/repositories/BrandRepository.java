package ecom.clothes.repositories;

import ecom.clothes.model.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @Query(value = "select b from BrandEntity b where lower(b.brandName) like :brandName")
    Page<BrandEntity> findByBrandName(String brandName, Pageable pageable);

    BrandEntity findByName(String brandName);
}
