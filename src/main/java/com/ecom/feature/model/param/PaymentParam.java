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
public class PaymentParam {

    @NotNull(message = "userId의 값은 필수입니다.")
    @Positive(message = "userId 값은 1 이상이어야 합니다.")
    @Schema(description = "유저 식별 ID", example = "1")
    private Long userId;

    @NotNull(message = "productId는 필수 입니다.")
    @Positive(message = "productId 값은 1 이상이어야 합니다.")
    @Schema(description = "상품 식별 ID", example = "1")
    private Long productId;
}
