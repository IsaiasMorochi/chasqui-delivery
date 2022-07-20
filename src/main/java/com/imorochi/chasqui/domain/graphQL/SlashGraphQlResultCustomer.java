package com.imorochi.chasqui.domain.graphQL;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SlashGraphQlResultCustomer {
    private GraphQlDataCustomer data;
    private Object extensions;
}
