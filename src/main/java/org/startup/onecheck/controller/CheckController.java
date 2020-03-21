package org.startup.onecheck.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.service.CheckService;
import org.startup.onecheck.service.ProductService;

@RestController
@AllArgsConstructor
public class CheckController {

    private ProductService productService;

    private CheckService checkService;

    @PostMapping("addProduct")
    public void addProductToCheck(@RequestBody ProductDto productDto){
        ProductDto productBase = productService.findById(productDto.getId());
        checkService.addProduct(productBase);
    }
}
