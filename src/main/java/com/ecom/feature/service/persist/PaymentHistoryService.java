package com.ecom.feature.service.persist;

import com.ecom.feature.model.entity.PaymentHistoryEntity;
import com.ecom.feature.repository.PaymentJpaRepository;
import com.ecom.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentHistoryService {

    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentHistoryEntity savePaymentHistory(PaymentHistoryEntity entity){
        PaymentHistoryEntity resEntity = paymentJpaRepository.save(entity);

        log.info("PaymentHistoryService/savePaymentHistory : {}", Utils.toJson(resEntity));
        return resEntity;
    }





}
