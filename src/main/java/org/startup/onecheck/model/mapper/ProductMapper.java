package org.startup.onecheck.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.model.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto (Product Product);

    Product productDtoToProduct (ProductDto productDto);

    List<Product> productDtoToProductList (List<ProductDto> productDtos);

    List<ProductDto> productToProductDtoList (List<Product> products);
}
