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
@NoArgsConstructor
@AllArgsConstructor
public class FetchUserPointParam {

    @NotNull(message = "userId는 필수입니다.")
    @Positive(message = "userId는 1 이상이어야 합니다.")
    @Schema(description = "조회할 userId", example = "1")
    Long userId;



}
