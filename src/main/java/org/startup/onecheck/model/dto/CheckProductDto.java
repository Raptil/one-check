package org.startup.onecheck.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckProductDto {

    private ProductDto product;

    private long count;
}
