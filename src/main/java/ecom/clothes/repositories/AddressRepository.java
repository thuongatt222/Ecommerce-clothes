package ecom.clothes.repositories;

import ecom.clothes.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findByUser_Id(Long userId);

}
