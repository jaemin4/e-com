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
public class ResTopFiveProductDto {

    @Schema(description = "상품 식별 ID", example = "1")
    private Long productId;

    @Schema(description = "상품 이름", example = "마우스")
    private String productName;

    @Schema(description = "팔린 갯수", example = "10")
    private Long count;

}
