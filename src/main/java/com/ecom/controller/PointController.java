package com.ecom.controller;


import com.ecom.feature.model.param.ChargeUserPointParam;
import com.ecom.feature.model.param.FetchUserPointParam;
import com.ecom.feature.model.param.UseUserPointParam;
import com.ecom.feature.model.result.ResChargeUserPointDto;
import com.ecom.feature.model.result.ResFetchUserPointDto;
import com.ecom.feature.model.result.ResUseUserPointDto;
import com.ecom.feature.service.front.PointFrontService;
import com.ecom.response.RestPointCharge;
import com.ecom.response.RestPointFetchById;
import com.ecom.response.RestPointUse;
import com.ecom.util.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/point")
@Tag(name = "포인트", description = "포인트 관련 API")
public class PointController {

    private final PointFrontService pointFrontService;

    @PostMapping("/fetchById")
    @Operation(
            summary = "유저 포인트 조회",
            description = "userId에 해당하는 사용자의 포인트를 조회한다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공 시 [status,message,data] 형식으로 반환",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestPointFetchById.class)
            )
    )
    public RestPointFetchById fetchUserPoint(@RequestBody @Valid FetchUserPointParam param) {
        log.info("/point/fetchById : {}", Utils.toJson(param));

        ResFetchUserPointDto resDto = pointFrontService.fetchUserPoint(param);
        return new RestPointFetchById("200", "성공적으로 조회하였습니다.", resDto);
    }

    @PostMapping("/charge")
    @Operation(summary = "포인트 충전", description = "특정 유저의 포인트를 충전한다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공 시 [status,message,data] 형식으로 반환",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestPointCharge.class)
            )
    )
    public RestPointCharge chargeUserPoint(@RequestBody @Valid ChargeUserPointParam param){
        log.info("/point/charge : {}", Utils.toJson(param));

        ResChargeUserPointDto resDto = pointFrontService.chargeUserPoint(param);
        return new RestPointCharge("200", "충전이 완료되었습니다.", resDto);
    }

    @PostMapping("/use")
    @Operation(summary = "포인트 사용", description = "특정 유저의 포인트를 사용한다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공 시 [status,message,data] 형식으로 반환",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestPointUse.class)
            )
    )
    public RestPointUse useUserPoint(@RequestBody @Valid UseUserPointParam param){
        log.info("/point/use : {}", Utils.toJson(param));

        ResUseUserPointDto resDto = pointFrontService.useUserPoint(param);
        return new RestPointUse("200","사용이 완료되었습니다.", resDto);
    }


}
