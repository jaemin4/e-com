package com.ecom.response;

import com.ecommerce.feature.any.model.result.ResChargeUserPointDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestPointCharge {

    @Schema(description = "응답 코드", example = "200")
    private String status;

    @Schema(description = "응답 메시지", example = "충전이 완료되었습니다.")
    private String message;

    @Schema(description = "데이터")
    private ResChargeUserPointDto data;


}
