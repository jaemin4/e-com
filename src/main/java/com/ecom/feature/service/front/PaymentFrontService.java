package com.ecom.feature.service.front;

import com.ecom.exception.PaymentRuntimeException;
import com.ecom.feature.model.PaymentMethodEnum;
import com.ecom.feature.model.PaymentStatusEnum;
import com.ecom.feature.model.entity.*;
import com.ecom.feature.model.param.PaymentParam;
import com.ecom.feature.model.result.ResPaymentDto;
import com.ecom.feature.service.persist.*;
import com.ecom.feature.service.third.PaymentMockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class PaymentFrontService {

    private final PaymentHistoryService paymentHistoryService;
    private final PointService pointService;
    private final PointHistoryService pointHistoryService;
    private final ProductService productService;
    private final UserService userService;
    private final PaymentMockService paymentMockService;

    @Transactional
    public ResPaymentDto payment(PaymentParam param){
        //유저 존재 확인
        UserEntity findUserEntity = userService.findByUserId(param.getUserId());

        // TODO 포인트 조회 비관적락
        PointEntity findPointEntity = pointService.findByUserIdForLock(param.getUserId());

        // TODO 상품 조회 비관적락
        ProductEntity findProductEntity = productService.fetchProductIdLockForUpdate(param.getProductId());

        if(findProductEntity.getProductQuantity() < 1){
            throw new PaymentRuntimeException("재고가 소진되었습니다.");
        }

        Long productPrice = findProductEntity.getProductPrice();
        Long userPoint = findPointEntity.getPoint();

        if(productPrice > userPoint){
            throw new PaymentRuntimeException("잔액이 부족합니다");
        }
        Long updateAmount = userPoint - productPrice;

        // TODO 포인트 update
        findPointEntity.setPoint(updateAmount);
        findPointEntity.setUpdateAt(OffsetDateTime.now(ZoneOffset.UTC));
        PointEntity pointSaveResEntity = pointService.updateUserPoint(findPointEntity);


        // TODO 공유자원이 아니기 떄문에 락X
        pointHistoryService.savePointHistory(new PointHistoryEntity(
                findUserEntity,
                productPrice * (-1),
                OffsetDateTime.now(ZoneOffset.UTC)
        ));

        //TODO 외부 api 동기 통신해서 만약 200받으면
        String result = paymentMockService.callMockApiAndGetStatus();

        PaymentStatusEnum paymentStatus = PaymentStatusEnum.SUCCESS;
        if(!result.equals("200")){
            paymentStatus = PaymentStatusEnum.FAILED;
            throw new PaymentRuntimeException("결제중 오류가 발생했습니다.");
        }
        // TODO 재고 감소
        findProductEntity.setProductId(findProductEntity.getProductId());
        findProductEntity.setProductQuantity(findProductEntity.getProductQuantity()-1);
        productService.updateProduct(findProductEntity);

        // TODO 결제내역 저장
        PaymentHistoryEntity payResEnt = paymentHistoryService.savePaymentHistory(
                new PaymentHistoryEntity(
                        findUserEntity,
                        findProductEntity,
                        productPrice,
                        PaymentMethodEnum.POINT,
                        paymentStatus,
                        OffsetDateTime.now()

                )
        );

        return new ResPaymentDto(payResEnt.getPaymentId());
    }


}
