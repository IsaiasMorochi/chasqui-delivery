package com.imorochi.chasqui.domain.graphQL;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.imorochi.chasqui.domain.document.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonRootName(value = "data")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GraphQlDataRating {
    private List<Rating> queryRating;
}
