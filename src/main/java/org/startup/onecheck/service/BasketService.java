package org.startup.onecheck.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.model.entity.Basket;
import org.startup.onecheck.model.entity.Check;
import org.startup.onecheck.model.mapper.BasketMapper;
import org.startup.onecheck.model.mapper.ProductMapper;
import org.startup.onecheck.repository.BasketRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketService {

    private BasketRepository basketRepository;

    private BasketMapper basketMapper;

    private ProductMapper productMapper;

    public List<ProductDto> getCurrentBasket() {
        Optional<Basket> basket = basketRepository.findById(1L);
        Optional<List<ProductDto>> productDtos = basket.map(Basket::getChecks)
                .map(checks -> checks.iterator().next())
                .map(Check::getProducts)
                .map(products -> productMapper.productToProductDtoList(products));
        return productDtos.orElse(null);
    }

}
