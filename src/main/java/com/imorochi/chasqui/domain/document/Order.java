package com.imorochi.chasqui.domain.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer orderId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date shipDate;
    private String status;
    private Boolean complete;
}
