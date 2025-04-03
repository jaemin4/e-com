package com.ecom.feature.repository;

import com.ecom.feature.model.entity.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentHistoryEntity,Long> {



}
