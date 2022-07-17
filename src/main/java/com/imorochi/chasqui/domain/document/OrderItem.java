package com.imorochi.chasqui.domain.document;

import lombok.Data;

@Data
public class OrderItem {
    private Integer productId;
    private Integer quantity;
}
