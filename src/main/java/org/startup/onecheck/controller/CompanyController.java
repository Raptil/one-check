package org.startup.onecheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.startup.onecheck.model.dto.CompanyDto;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.service.CompanyService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("products/")
    public List<ProductDto> getProductsByCompany(){
        CompanyDto companyDto = companyService.findCompanyByName("Lidl");
        return companyDto.getProducts();
    }
}
