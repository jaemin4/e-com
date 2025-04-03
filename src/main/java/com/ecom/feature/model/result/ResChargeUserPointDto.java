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
public class ResChargeUserPointDto {

    @Schema(description = "포인트 식별 ID", example = "1")
    Long pointId;

    @Schema(description = "조회된 userId", example = "1")
    Long userId;

    @Schema(description = "조회된 최종 금액", example = "8000")
    Long finalUserPoint;

    @Schema(description = "최조 포인트 생성일자", example = "2025-03-30T12:34:56")
    OffsetDateTime pointCreateAt;

    @Schema(description = "최근 충전반영 일자",example = "2025-03-30T15:40:56")
    OffsetDateTime pointUpdateAt;

}
