package com.ecom.controller;


import com.ecom.feature.model.param.FetchUserPointParam;
import com.ecom.feature.model.result.ResFetchUserPointDto;
import com.ecom.feature.service.front.PointFrontService;
import com.ecom.response.RestPointFetchById;
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






}
