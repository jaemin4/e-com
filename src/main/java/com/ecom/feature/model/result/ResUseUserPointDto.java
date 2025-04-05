package com.ecom.feature.model.result;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUseUserPointDto {

    @Schema(description = "사용 포인트 식별 ID", example = "1")
    Long pointId;

    @Schema(description = "사용한 userId", example = "1")
    Long userId;

    @Schema(description = "사용 후 업데이트 금액", example = "8000")
    Long finalUserPoint;

    @Schema(description = "최조 포인트 생성일자", example = "2025-03-30T12:34:56")
    OffsetDateTime pointCreateAt;

    @Schema(description = "최근 사용 반영일자",example = "2025-03-30T15:40:56")
    OffsetDateTime pointUpdateAt;

}
