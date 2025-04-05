package com.ecom.feature.repository;

import com.ecom.feature.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRespository extends JpaRepository<UserEntity,Long> {
}
