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
@Table(name = "t_point")
public class PointEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long pointId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;

    public PointEntity(Long pointId, UserEntity user, Long point, OffsetDateTime updateAt) {
        this.pointId = pointId;
        this.user = user;
        this.point = point;
        this.updateAt = updateAt;
    }

    public PointEntity(UserEntity user, Long point, OffsetDateTime createAt) {
        this.user = user;
        this.point = point;
        this.createAt = createAt;
    }

}
