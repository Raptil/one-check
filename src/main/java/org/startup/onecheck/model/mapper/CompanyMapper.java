package org.startup.onecheck.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.startup.onecheck.model.dto.CompanyDto;
import org.startup.onecheck.model.entity.Company;

import java.util.List;

@Mapper
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDto companyToCompanyDto (Company company);

    Company companyDtoToCompany (CompanyDto companyDto);

    List<Company> companyDtoToCompanyList (List<CompanyDto> companyDtos);

    List<CompanyDto> companyToCompanyDtoList (List<Company> companys);
}
