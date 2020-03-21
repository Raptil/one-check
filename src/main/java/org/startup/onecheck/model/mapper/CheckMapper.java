package org.startup.onecheck.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.startup.onecheck.model.dto.CheckDto;
import org.startup.onecheck.model.entity.Check;

import java.util.List;

@Mapper
public interface CheckMapper {

    CheckMapper INSTANCE = Mappers.getMapper(CheckMapper.class);

    CheckDto checkToCheckDto (Check check);

    Check checkDtoToCheck (CheckDto checkDto);

    List<Check> checkDtoToCheckList (List<CheckDto> checkDtos);

    List<CheckDto> checkToCheckDtoList (List<Check> checks);
}
