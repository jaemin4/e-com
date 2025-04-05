package com.ecom.response;

import com.ecom.feature.model.result.ResIssueCouponDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestIssueCoupon {

    @Schema(description = "반환 상태", example = "200")
    private String status;

    @Schema(description = "응답 메시지", example = "쿠폰 발급이 성공되었습니다.")
    private String message;

    @Schema(description = "데이터")
    private ResIssueCouponDto data;

}
