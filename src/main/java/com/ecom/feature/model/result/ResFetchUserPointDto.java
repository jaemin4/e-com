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
public class ResFetchUserPointDto {

    @Schema(description = "조회된 userId", example = "1")
    Long userId;

    @Schema(description = "조회 최종 포인트", example = "5000")
    Long point;


}
