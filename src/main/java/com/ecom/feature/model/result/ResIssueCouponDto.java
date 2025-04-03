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
public class ResIssueCouponDto {

    @Schema(description = "유저 쿠폰 식별 ID", example = "1")
    private Long userCouponId;

}
