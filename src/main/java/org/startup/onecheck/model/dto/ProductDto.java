package org.startup.onecheck.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String productName;

    private String category;

    private int price;

    private String description;
}
