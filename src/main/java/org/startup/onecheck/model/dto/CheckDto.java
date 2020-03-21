package org.startup.onecheck.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.startup.onecheck.model.entity.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckDto {

    private Long id;

    private String description;

    private String totalPrice;

    private List<Product> products;
}
