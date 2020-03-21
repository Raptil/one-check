package org.startup.onecheck.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.model.entity.Product;
import org.startup.onecheck.model.mapper.ProductMapper;
import org.startup.onecheck.repository.ProductRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    public ProductDto findById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return productMapper.productToProductDto(optionalProduct.orElse(null));
    }
}
