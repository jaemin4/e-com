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
public class FetchProductParam {

    @NotNull(message = "productId값은 필수입니다.")
    @Positive(message = "productId의 값은 1 이상이어야 합니다.")
    @Schema(description = "조회할 productId", example = "1")
    private Long productId;

}
