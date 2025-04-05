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
public class UseUserPointParam {
    @NotNull(message = "userId값은 필수입니다.")
    @Positive(message = "userId의 값은 1 이상이어야 합니다.")
    @Schema(description = "사용할 userId", example = "1")
    private Long userId;

    @NotNull(message = "amount 값은 필수입니다.")
    @Positive(message = "amount 값은 1 이상이어야 합니다.")
    @Schema(description = "사용할 금액", example = "5000")
    private Long amount;
}
