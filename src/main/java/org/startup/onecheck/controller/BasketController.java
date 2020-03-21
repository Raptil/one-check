package org.startup.onecheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.service.BasketService;

import java.util.List;

@RestController
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @GetMapping("basket/")
    public List<ProductDto> getProductsByCompany(){
        return basketService.getCurrentBasket();
    }
}
