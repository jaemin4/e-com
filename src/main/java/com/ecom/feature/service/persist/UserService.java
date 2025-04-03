package com.ecom.feature.service.persist;

import com.ecom.feature.model.entity.UserEntity;
import com.ecom.feature.repository.UserJpaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRespository userJpaRespository;

    public UserEntity findByUserId(Long userId){
        return userJpaRespository.findById(userId).orElseThrow(
                () -> new RuntimeException("사용자를 찾을 수 없습니다.")
        );
    }


}
