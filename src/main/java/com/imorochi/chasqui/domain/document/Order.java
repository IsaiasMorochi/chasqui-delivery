package com.imorochi.chasqui.domain.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imorochi.chasqui.infrastructure.utils.helper.Indices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = Indices.ORDER_INDEX)
@Setting(settingPath = "static/settings.json")
public class Order {

    @Id
    @Field(type = FieldType.Keyword)
    private Integer orderId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Field(type = FieldType.Date)
    private Date shipDate;

    @Field(type = FieldType.Text)
    private String status;

    @Field(type = FieldType.Boolean)
    private Boolean complete;

}
