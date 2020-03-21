package org.startup.onecheck.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.startup.onecheck.model.dto.CheckProductDto;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.model.entity.Basket;
import org.startup.onecheck.model.entity.Check;
import org.startup.onecheck.model.mapper.ProductMapper;
import org.startup.onecheck.repository.BasketRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BasketService {

    private BasketRepository basketRepository;

    private ProductMapper productMapper;

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

}
