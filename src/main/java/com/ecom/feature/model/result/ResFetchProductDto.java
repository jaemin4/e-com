package com.ecom.feature.model.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResFetchProductDto {

    @Schema(description = "상품 식별 ID", example = "1")
    private Long productId;

    @Schema(description = "상품 이름 ", example = "컴퓨터")
    private String productName;

    @Schema(description = "상품 가격", example = "1000000")
    private Long productPrice;

    @Schema(description = "상품 수량", example = "5")
    private Long productQuantity;


}
