package com.ecom.feature.service.third;

import com.ecom.feature.model.mock.MockPaymentResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentMockService {
    public String callMockApiAndGetStatus() {
        String url = "https://67ed717f4387d9117bbda6b1.mockapi.io/api/pay/test";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<MockPaymentResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MockPaymentResponse>>() {}
        );

        List<MockPaymentResponse> body = response.getBody();

        if (body != null && !body.isEmpty()) {
            return body.get(0).getStatus();
        } else {
            return "NO_RESPONSE";
        }
    }

}
