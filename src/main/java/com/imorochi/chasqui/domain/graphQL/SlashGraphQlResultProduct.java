package com.imorochi.chasqui.domain.graphQL;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SlashGraphQlResultProduct {
    private GraphQlDataProduct data;
    private Object extensions;
}
