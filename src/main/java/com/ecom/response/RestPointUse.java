package com.ecom.response;

import com.ecom.feature.model.result.ResUseUserPointDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestPointUse{

    @Schema(description = "반환 상태", example = "200")
    private String status;

    @Schema(description = "응답 메시지", example = "포인트 사용에 성공하였습니다.")
    private String message;

    @Schema(description = "데이터")
    private ResUseUserPointDto data;

}
