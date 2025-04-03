package com.ecom.feature.service.persist;

import com.ecom.feature.model.entity.PointEntity;
import com.ecom.feature.model.entity.UserEntity;
import com.ecom.feature.repository.PointJpaRepository;
import com.ecom.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {

    private final PointJpaRepository pointJpaRepository;

    public PointEntity fetchUserPointByUserId(UserEntity userEntity){

        return pointJpaRepository.findByUser(userEntity).orElseThrow(
                () -> new PointRuntimeException(Utils.toJson(userEntity.getUserId()) + " 해당하는 포인트값이 존재하지 않습니다.")
        );
    }




}
