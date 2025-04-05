package com.ecom.controller;

import com.ecom.feature.model.param.PaymentParam;
import com.ecom.feature.model.result.ResPaymentDto;
import com.ecom.feature.service.front.PaymentFrontService;
import com.ecom.response.RestPaymentPay;
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
@RequestMapping("/payment")
@Tag(name = "결제", description = "결제 API")
public class PaymentController {

    private final PaymentFrontService paymentFrontService;
    @PostMapping("/pay")
    @Operation(summary = "결제", description = "결제 진행")
    @ApiResponse(
            responseCode = "200",
            description = "성공 시 [status,message,data] 형식으로 반환",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestPaymentPay.class)

            )
    )
    public RestPaymentPay payment(@RequestBody @Valid PaymentParam param){
        log.info("/payment/pay : {}", Utils.toJson(param));

        ResPaymentDto resDto = paymentFrontService.payment(param);
        return new RestPaymentPay("200","결제가 완료되었습니다.",resDto);
    }



}
