package com.imorochi.chasqui.application.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagedRequestDTO {
    private static final int DEFAULT_SIZE = 100;
    private int page;
    private int size;

    public int getSize() {
        return size != 0 ? size : DEFAULT_SIZE;
    }

}
