package com.ecom.feature.service.persist;

import com.ecom.feature.model.entity.UserCouponEntity;
import com.ecom.feature.repository.UserCouponJpaRepository;
import com.ecom.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCouponService {

    private final UserCouponJpaRepository userCouponJpaRepository;

    public UserCouponEntity saveUserCoupon (UserCouponEntity entity){
        UserCouponEntity resEntity = userCouponJpaRepository.save(entity);

        log.info("UserCouponService/saveUserCoupon : {}", Utils.toJson(resEntity));
        return resEntity;
    }


}
