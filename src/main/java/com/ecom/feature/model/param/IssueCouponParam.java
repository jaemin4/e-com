package com.ecom.feature.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueCouponParam {

    @NotNull
    @Positive
    @Schema(description = "받급원하는 couponId", example = "1")
    private Long couponId;

    @NotNull
    @Positive
    @Schema(description = "유저 식별 번호", example = "1")
    private Long userId;

}
