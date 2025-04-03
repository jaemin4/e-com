package com.ecom.feature.repository;

import com.ecom.feature.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long> {



}
