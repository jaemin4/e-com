package com.ecom.feature.service.persist;

import com.ecom.feature.model.entity.PointHistoryEntity;
import com.ecom.feature.repository.PointHistoryJpaRepository;
import com.ecom.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointHistoryService {

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    public PointHistoryEntity savePointHistory(PointHistoryEntity entity){
        PointHistoryEntity resPointHistoryEntity = pointHistoryJpaRepository.save(entity);

        log.info("PointHistoryService/savePointHistory : {}", Utils.toJson(resPointHistoryEntity));
        return resPointHistoryEntity;
    }


}
