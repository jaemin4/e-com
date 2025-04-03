package com.ecom.feature.service.persist;

import com.ecom.exception.ProductRuntimeException;
import com.ecom.feature.model.entity.ProductEntity;
import com.ecom.feature.repository.ProductJpaRepository;
import com.ecom.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductJpaRepository productJpaRepository;

    // TODO 단순 조회 : 상품 재고 감소 기능에는 WRITE 레벨의 락이기 때문에, 공유자원 동시성 방지
    public ProductEntity fetchByProductId(Long productId){
        ProductEntity entity = productJpaRepository.findById(productId).orElseThrow(
                () -> new ProductRuntimeException("해당 상품이 존재하지 않습니다.")
        );
        return entity;
    }


}
