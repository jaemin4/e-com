package com.ecom.feature.service.front;

import com.ecom.exception.PointRuntimeException;
import com.ecom.feature.model.entity.PointEntity;
import com.ecom.feature.model.entity.PointHistoryEntity;
import com.ecom.feature.model.entity.UserEntity;
import com.ecom.feature.model.param.ChargeUserPointParam;
import com.ecom.feature.model.param.FetchUserPointParam;
import com.ecom.feature.model.param.UseUserPointParam;
import com.ecom.feature.model.result.ResChargeUserPointDto;
import com.ecom.feature.model.result.ResFetchUserPointDto;
import com.ecom.feature.model.result.ResUseUserPointDto;
import com.ecom.feature.service.persist.PointHistoryService;
import com.ecom.feature.service.persist.PointService;
import com.ecom.feature.service.persist.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class PointFrontService {

    private final PointService pointService;
    private final PointHistoryService pointHistoryService;
    private final UserService userService;
    /*
        TODO 같은 공유자원에 대해서 데이터베이스 트랜잭션중에 실행시 Lock 걸려서 끝나고 조회가능
    */
    public ResFetchUserPointDto fetchUserPoint(FetchUserPointParam param){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(param.getUserId());
        PointEntity pointResEntity = pointService.fetchUserPointByUserId(userEntity);

        return new ResFetchUserPointDto(pointResEntity.getUser().getUserId(), pointResEntity.getPoint());
    }

    /*
           TODO 잔액 충전기능
           todo 비관적락을 이용한 동시성 제어

       */
    @Transactional
    public ResChargeUserPointDto chargeUserPoint(ChargeUserPointParam param){
        //유저 존재 확인
        UserEntity findUserEntity = userService.findByUserId(param.getUserId());

        //point없으면 유저 등록
        pointService.saveIfNotExists(findUserEntity);

        //조회 시점에 비관적락
        PointEntity findEntity = pointService.findByUserIdForLock(param.getUserId());
        Long updateAmount = findEntity.getPoint() + param.getAmount();

        //잔액 업데이트
        findEntity.setPoint(updateAmount);
        findEntity.setUpdateAt(OffsetDateTime.now(ZoneOffset.UTC));
        PointEntity pointSaveResEntity = pointService.updateUserPoint(findEntity);

        //공유자원이 아니기 떄문에 락X
        pointHistoryService.savePointHistory(new PointHistoryEntity(
                findUserEntity,
                param.getAmount(),
                OffsetDateTime.now(ZoneOffset.UTC)
        ));

        return new ResChargeUserPointDto(
                pointSaveResEntity.getPointId(),
                findUserEntity.getUserId(),
                pointSaveResEntity.getPoint(),
                pointSaveResEntity.getCreateAt(),
                pointSaveResEntity.getUpdateAt()
        );
    }

    /*
          TODO 잔액 사용기능
          TODO 비관적락을 이용한 동시성 제어
       */
    @Transactional
    public ResUseUserPointDto useUserPoint(UseUserPointParam param){
        //유저 존재 확인
        UserEntity findUserEntity = userService.findByUserId(param.getUserId());

        //포인트 등록 없을때 최초 등록
        pointService.saveIfNotExists(findUserEntity);

        //조회 시점에 비관적락
        PointEntity findEntity = pointService.findByUserIdForLock(param.getUserId());

        if(param.getAmount() > findEntity.getPoint()){
            throw new PointRuntimeException("잔액이 부족합니다");
        }
        Long updateAmount = findEntity.getPoint() - param.getAmount();

        //잔액 업데이트
        findEntity.setPoint(updateAmount);
        findEntity.setUpdateAt(OffsetDateTime.now(ZoneOffset.UTC));
        PointEntity pointSaveResEntity = pointService.updateUserPoint(findEntity);

        //공유자원이 아니기 떄문에 락X
        pointHistoryService.savePointHistory(new PointHistoryEntity(
                pointSaveResEntity.getUser(),
                param.getAmount() * (-1),
                OffsetDateTime.now(ZoneOffset.UTC)
        ));

        return new ResUseUserPointDto(
                pointSaveResEntity.getPointId(),
                findUserEntity.getUserId(),
                pointSaveResEntity.getPoint(),
                pointSaveResEntity.getCreateAt(),
                pointSaveResEntity.getUpdateAt()
        );
    }


}
