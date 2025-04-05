package com.ecom.controller;

import com.ecom.feature.model.param.FetchProductParam;
import com.ecom.feature.model.result.ResFetchProductDto;
import com.ecom.feature.model.result.ResTopFiveProductDto;
import com.ecom.feature.service.front.ProductFrontService;
import com.ecom.response.RestFetchProduct;
import com.ecom.response.RestTopFiveProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "상품", description = "상품 관련 API")
public class ProductController {

    private final ProductFrontService productFrontService;

    /*
        TODO 상품 주문결제 진행시 LOCK 걸어서 해당 자원에 대해서 접근제한, 락수준 : WRITE
     */
    @PostMapping("/fetchById")
    @Operation(summary = "상품조회", description = "productId의 해당하는 상품조회")
    @ApiResponse(
            responseCode = "200",
            description = "성공 시 [status,message,data] 형식으로 반환",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestFetchProduct.class)
            )
    )
    public RestFetchProduct fetchProduct(@RequestBody @Valid FetchProductParam param){
        ResFetchProductDto resDto = productFrontService.fetchProduct(param);
        return new RestFetchProduct("200","상품 조회에 성공하였습니다.",resDto);
    }

    @PostMapping("/topFive")
    @Operation(summary = "인기 상품조회", description = "가장 많이 팔린 상위 5개의 상품")
    @ApiResponse(
            responseCode = "200",
            description = "성공 시 [status,message,data] 형식으로 반환",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestTopFiveProduct.class)
            )
    )
    public RestTopFiveProduct topFiveProduct(){
        ResTopFiveProductDto resDto = new ResTopFiveProductDto();
        return new RestTopFiveProduct("200","상위 5개의 상품 조회에 성공하였습니다.",resDto);
    }





}
