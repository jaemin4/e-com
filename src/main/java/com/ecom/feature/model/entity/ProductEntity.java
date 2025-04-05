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
@Table(name = "t_product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", length = 77)
    private String productName;

    @Column(name = "product_price")
    private Long productPrice;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;


}
