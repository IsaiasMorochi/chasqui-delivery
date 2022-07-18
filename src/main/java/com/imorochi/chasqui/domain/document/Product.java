package com.imorochi.chasqui.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;

    private Integer productId;

    private Category category;

    private String productName;

    private String productPrice;

    private String productPhotoUrl;

    private String productStatus;

    private List<Tag> tags;

}
