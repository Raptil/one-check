package org.startup.onecheck.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.startup.onecheck.model.dto.BasketDto;
import org.startup.onecheck.model.entity.Basket;

import java.util.List;

@Mapper
public interface BasketMapper {

    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

    BasketDto basketToBasketDto (Basket basket);

    Basket basketDtoToBasket (BasketDto basketDto);

    List<Basket> basketDtoToBasketList (List<BasketDto> basketDtos);

    List<BasketDto> basketToBasketDtoList (List<Basket> baskets);
}
