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

}
