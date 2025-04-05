package com.ecom.feature.repository;


import com.ecom.feature.model.entity.PointEntity;
import com.ecom.feature.model.entity.UserEntity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointJpaRepository extends JpaRepository<PointEntity,Long> {


    Optional<PointEntity> findByUser(UserEntity user);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "5000")
    })
    @Query("SELECT p FROM PointEntity p WHERE p.user = :user")
    Optional<PointEntity> findByUserIdForLock(@Param("user") Long user);



}
