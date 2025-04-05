package com.ecom.feature.model.entity;

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
@Table(name = "t_user_coupon")
public class UserCouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_coupon_id")
    private Long userCouponId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private CouponEntity coupon;

    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @Column(name = "used", nullable = false)
    private Long used;

    @Column(name = "used_at")
    private OffsetDateTime usedAt;
    public UserCouponEntity(UserEntity user, CouponEntity coupon, OffsetDateTime issuedAt, Long used) {
        this.user = user;
        this.coupon = coupon;
        this.issuedAt = issuedAt;
        this.used = used;
    }


}
