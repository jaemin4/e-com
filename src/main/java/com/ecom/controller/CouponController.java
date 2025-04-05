package com.ecom.controller;

import com.ecom.feature.model.param.IssueCouponParam;
import com.ecom.feature.model.result.ResIssueCouponDto;
import com.ecom.feature.service.front.CouponFrontService;
import com.ecom.response.RestIssueCoupon;
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
@RequestMapping("/coupon")
@Tag(name = "쿠폰", description = "쿠폰 API")
public class CouponController {

    private final CouponFrontService couponFrontService;


    @PostMapping("/issue")
    @Operation(summary = "쿠폰 발급", description = "쿠폰을 선착순 발급")
    @ApiResponse(
            responseCode = "200",
            description = "성공 시 [status,message,data] 형식으로 반환",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestIssueCoupon.class)
            )
    )
    public RestIssueCoupon issueCoupon(@RequestBody @Valid IssueCouponParam param){
        log.info("/point/use : {}", Utils.toJson(param));
        ResIssueCouponDto resDto = couponFrontService.issueCoupon(param);

        return new RestIssueCoupon("200","쿠폰이 발급되었습니다.",resDto);
    }

}
