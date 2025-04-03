package com.ecom.feature.service.front;

import com.ecom.feature.model.entity.PointEntity;
import com.ecom.feature.model.entity.UserEntity;
import com.ecom.feature.model.param.FetchUserPointParam;
import com.ecom.feature.model.result.ResFetchUserPointDto;
import com.ecom.feature.service.persist.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointFrontService {

    private final PointService pointService;
    /*
        TODO 같은 공유자원에 대해서 데이터베이스 트랜잭션중에 실행시 Lock 걸려서 끝나고 조회가능
    */
    public ResFetchUserPointDto fetchUserPoint(FetchUserPointParam param){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(param.getUserId());
        PointEntity pointResEntity = pointService.fetchUserPointByUserId(userEntity);

        return new ResFetchUserPointDto(pointResEntity.getUser().getUserId(), pointResEntity.getPoint());
    }





}
