package org.startup.onecheck.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.startup.onecheck.model.dto.CheckDto;
import org.startup.onecheck.model.dto.CheckProductDto;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.model.entity.Basket;
import org.startup.onecheck.model.entity.Check;
import org.startup.onecheck.model.mapper.CheckMapper;
import org.startup.onecheck.model.mapper.ProductMapper;
import org.startup.onecheck.repository.BasketRepository;
import org.startup.onecheck.repository.CheckRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BasketService {

    private BasketRepository basketRepository;

    private ProductMapper productMapper;

    private CheckMapper checkMapper;

    private CheckRepository checkRepository;

    public List<CheckProductDto> getCurrentBasket() {
        Optional<Basket> basket = basketRepository.findById(1L);
        Optional<List<ProductDto>> productDtos = basket.map(Basket::getChecks)
                .map(checks -> checks.iterator().next())
                .map(Check::getProducts)
                .map(products -> productMapper.productToProductDtoList(products));
        return map(Objects.requireNonNull(productDtos.orElse(null)));
    }

    private List<CheckProductDto> map(List<ProductDto> productDtos) {
        Map<ProductDto, Long> counts =
                productDtos.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        List<CheckProductDto> checkProductDtos = new ArrayList<>();
        counts.forEach((productDto, count) -> checkProductDtos.add(new CheckProductDto(productDto, count)));
        return checkProductDtos;
    }

    public List<CheckDto> getChecks() {
        List<CheckDto> checkDtos = checkMapper.checkToCheckDtoList(checkRepository.findAll()
                                                                                  .stream()
                                                                                  .filter(check -> check.getBasket() == null)
                                                                                  .collect(Collectors.toList()));
        return checkDtos;
    }

    public List<CheckDto> getChecksByBasket() {
        List<CheckDto> checkDtos = checkMapper.checkToCheckDtoList(checkRepository.findAll()
                .stream()
                .filter(check ->check.getBasket()!=null && check.getBasket().getId() == 1L)
                .collect(Collectors.toList()));
        return checkDtos;
    }

    public void addCheck(CheckDto checkDto){
        Optional<Check> optionalCheck = checkRepository.findById(checkDto.getId());
        Check check = (Check) optionalCheck.orElse(null);
        Optional<Basket> basketOptional = basketRepository.findById(1L);
        Basket basket = (Basket) basketOptional.orElse(null);
        check.setBasket(basket);
        if (!basket.getChecks().contains(check))  basket.getChecks().add(check);
        basketRepository.saveAndFlush(basket);
    }

}
