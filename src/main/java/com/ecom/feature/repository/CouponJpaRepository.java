package com.ecom.feature.repository;

import com.ecom.feature.model.entity.CouponEntity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponJpaRepository extends JpaRepository<CouponEntity,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "5000")
    })
    @Query("SELECT p FROM CouponEntity p WHERE p.couponId = :couponId")
    Optional<CouponEntity> findByCouponIdForLock(Long couponId);


}
