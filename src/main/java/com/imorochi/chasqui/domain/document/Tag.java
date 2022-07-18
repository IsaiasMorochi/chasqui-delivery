package com.imorochi.chasqui.domain.document;

import com.imorochi.chasqui.domain.helper.Indices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = Indices.TAG_INDEX)
@Setting(settingPath = "static/settings.json")
public class Tag implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;

    @Id
    @Field(type = FieldType.Keyword)
    private Integer tagId;

    @Field(type = FieldType.Text)
    private String tagName;
}
