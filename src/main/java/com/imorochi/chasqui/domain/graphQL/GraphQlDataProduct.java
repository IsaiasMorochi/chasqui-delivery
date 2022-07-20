package com.imorochi.chasqui.domain.graphQL;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.imorochi.chasqui.domain.document.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonRootName(value = "data")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GraphQlDataProduct {
    private List<Product> queryArtist;
}
