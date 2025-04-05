package com.ecom.feature.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "t_point_history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PointHistoryEntity {

    @Column(name = "point_history_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "amount", nullable = false)
    Long amount;

    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;

    public PointHistoryEntity(UserEntity user, Long amount, OffsetDateTime createAt) {
        this.user = user;
        this.amount = amount;
        this.createAt = createAt;
    }

}
