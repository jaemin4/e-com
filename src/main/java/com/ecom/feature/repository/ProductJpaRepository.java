package com.ecom.feature.repository;

import com.ecom.feature.model.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "5000")
    })
    @Query("SELECT p FROM ProductEntity p WHERE p.productId = :productId")
    Optional<ProductEntity> findByProductIdForLock(Long productId);

}
