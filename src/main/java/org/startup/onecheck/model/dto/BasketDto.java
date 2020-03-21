package org.startup.onecheck.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    private Long id;

    private int totalPrice;

    private String activeFlg;

    private List<CheckDto> checks;

    private UserDto user;
}
