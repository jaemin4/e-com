package com.ecom.feature.service.persist;

import com.ecom.exception.CouponRuntimeException;
import com.ecom.feature.model.entity.CouponEntity;
import com.ecom.feature.repository.CouponJpaRepository;
import com.ecom.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {

    private final CouponJpaRepository couponJpaRepository;

    public CouponEntity findByCouponIdForLock(Long couponId){
        return couponJpaRepository.findByCouponIdForLock(couponId).orElseThrow(
                () -> new CouponRuntimeException("해당 쿠폰을 찾을 수 없습니다.")
        );
    }
    public void updateCoupon(CouponEntity entity){
        CouponEntity resultEntity = couponJpaRepository.save(entity);

        log.info("CouponService/updateCoupon : {}", Utils.toJson(resultEntity));
    }



}
