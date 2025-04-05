package com.ecom.feature.model.mock;

import lombok.Data;

@Data
public class MockPaymentResponse {
    private String status;
    private String transactionId;
    private String message;
    private String id;
}
