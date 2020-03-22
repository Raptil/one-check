package org.startup.onecheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.startup.onecheck.model.dto.CheckDto;
import org.startup.onecheck.model.dto.CheckProductDto;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.service.BasketService;
import org.startup.onecheck.service.CheckService;

import java.util.List;

@RestController
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    private CheckService checkService;

    @GetMapping("basket/")
    public List<CheckProductDto> getProductsByCompany(){
        return basketService.getCurrentBasket();
    }

    @GetMapping("checks/")
    public List<CheckDto> getCheckByCompany(){return basketService.getChecks();}

    @GetMapping("checkBasket/")
    public List<CheckDto> getCheckByBasket(){return basketService.getChecksByBasket();}

    @PostMapping("addCheck")
    public void addCheckToBasket(@RequestBody CheckDto checkDto){
        basketService.addCheck(checkDto);
    }
}
