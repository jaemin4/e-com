package com.ecom.feature.repository;

import com.ecom.feature.model.entity.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointHistoryJpaRepository extends JpaRepository<PointHistoryEntity, Long> {
}
