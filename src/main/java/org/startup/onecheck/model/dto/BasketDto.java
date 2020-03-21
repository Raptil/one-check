package org.startup.onecheck.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.startup.onecheck.model.entity.Basket;
import org.startup.onecheck.model.entity.Check;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    private Long id;

    private int totalPrice;

    private String activeFlg;

    private List<Check> checks;

    private List<Basket> baskets;
}
