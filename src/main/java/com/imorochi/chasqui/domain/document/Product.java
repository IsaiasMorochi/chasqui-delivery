package com.imorochi.chasqui.domain.document;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private Category category;
    private String productName;
    private String productPhotoUrl;
    private String productStatus;
}
