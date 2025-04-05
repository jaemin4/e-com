package com.ecom.feature.service.front;

import com.ecom.exception.CouponRuntimeException;
import com.ecom.feature.model.entity.CouponEntity;
import com.ecom.feature.model.entity.UserCouponEntity;
import com.ecom.feature.model.entity.UserEntity;
import com.ecom.feature.model.param.IssueCouponParam;
import com.ecom.feature.model.result.ResIssueCouponDto;
import com.ecom.feature.service.persist.CouponService;
import com.ecom.feature.service.persist.UserCouponService;
import com.ecom.feature.service.persist.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CouponFrontService {

    private final CouponService couponService;
    private final UserCouponService userCouponService;
    private final UserService userService;

    @Transactional
    public ResIssueCouponDto issueCoupon(IssueCouponParam param){
        //유저 존재 확인
        UserEntity findUserEntity = userService.findByUserId(param.getUserId());

        //조회시점 부터 해당 자원 DB락
        CouponEntity resEntity = couponService.findByCouponIdForLock(param.getCouponId());

        if(resEntity.getCouponQuantity() < 1){
            throw new CouponRuntimeException("해당 쿠폰이 모두 소진되었습니다.");
        } else{
            //먼저 쿠폰 소진
            resEntity.setCouponQuantity(resEntity.getCouponQuantity()-1);
            couponService.updateCoupon(resEntity);

            //사용자에게 쿠폰 발급 -> 이부분은 자원을 공유하지 않기 때문에, Lock 제외해도?
            UserCouponEntity resUserCoupon = userCouponService.saveUserCoupon(new UserCouponEntity(
                findUserEntity,
                resEntity,
                OffsetDateTime.now(),
                1L
            ));
            return new ResIssueCouponDto(resUserCoupon.getUserCouponId());

        }


    }


}
