package com.ecom.feature.model.entity;

import com.ecom.feature.model.PaymentMethodEnum;
import com.ecom.feature.model.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_payment_history")
public class PaymentHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity productId;

    @Column(nullable = false)
    private Long payPoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodEnum paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum status;

    @Column(name = "payment_date")
    private OffsetDateTime paymentDate;

    public PaymentHistoryEntity(UserEntity user, ProductEntity productId, Long payPoint, PaymentMethodEnum paymentMethod, PaymentStatusEnum status, OffsetDateTime paymentDate) {
        this.user = user;
        this.productId = productId;
        this.payPoint = payPoint;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentDate = paymentDate;
    }
}
