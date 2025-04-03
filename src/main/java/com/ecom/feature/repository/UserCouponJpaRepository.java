package com.ecom.feature.repository;

import com.ecom.feature.model.entity.UserCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponJpaRepository extends JpaRepository<UserCouponEntity,Long> {
}
