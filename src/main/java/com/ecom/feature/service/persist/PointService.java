package com.ecom.feature.service.persist;

import com.ecom.exception.PointRuntimeException;
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

    public PointEntity updateUserPoint(PointEntity entity){
        log.info("PointService/chargeUserPoint : {}", Utils.toJson(entity));

        return pointJpaRepository.save(entity);
    }

    public void saveIfNotExists(UserEntity userEntity){
        pointJpaRepository.findByUser(userEntity)
                .orElseGet(() -> {
                    PointEntity savedEntity = pointJpaRepository.save(new PointEntity(
                            userEntity,0L, OffsetDateTime.now(ZoneOffset.UTC)
                    ));
                    log.info("PointService/saveIfNotExists : {}", Utils.toJson(savedEntity));
                    return savedEntity;
                });

    }

    public PointEntity findByUserIdForLock(Long user){
        return pointJpaRepository.findByUserIdForLock(user)
                .orElseThrow(() -> new PointRuntimeException("포인트 정보 없음"));
    }




}
