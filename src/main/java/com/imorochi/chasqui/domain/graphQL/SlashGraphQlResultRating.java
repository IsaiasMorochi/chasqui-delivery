package com.imorochi.chasqui.domain.graphQL;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SlashGraphQlResultRating {
    private GraphQlDataRating data;
    private Object extensions;
}
