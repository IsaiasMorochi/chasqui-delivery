package com.imorochi.chasqui.domain.document;

import com.imorochi.chasqui.infrastructure.utils.helper.Indices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = Indices.COMPANY_INDEX)
@Setting(settingPath = "static/settings.json")
public class Company {

    @Id
    @Field(type = FieldType.Keyword)
    private Integer companyId;

    @Field(type = FieldType.Text)
    private String companyName;
}
