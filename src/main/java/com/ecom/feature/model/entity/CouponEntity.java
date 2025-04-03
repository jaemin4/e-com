package com.ecom.feature.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_coupon")
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "discount_type", nullable = false)
    private String discountType; // "fixed"

    @Column(name = "discount_value", nullable = false)
    private Integer discountValue;// "10%"

    @Column(name = "coupon_quantity", nullable = false)
    private Integer couponQuantity;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCouponEntity> userCoupons;
}
