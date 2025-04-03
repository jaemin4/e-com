package com.ecom.feature.service.front;

import com.ecom.feature.model.entity.ProductEntity;
import com.ecom.feature.model.param.FetchProductParam;
import com.ecom.feature.model.result.ResFetchProductDto;
import com.ecom.feature.service.persist.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductFrontService {

    private final ProductService productService;

    public ResFetchProductDto fetchProduct(FetchProductParam param){

        ProductEntity entity = productService.fetchByProductId(param.getProductId());

        return new ResFetchProductDto(
                entity.getProductId(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getProductQuantity()
        );

    }



}
