package com.imorochi.chasqui.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rating {
    private String id;
    private double score;
    private Customer by;
    private Product about;
}
