package org.startup.onecheck.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.startup.onecheck.model.dto.CompanyDto;
import org.startup.onecheck.model.mapper.CompanyMapper;
import org.startup.onecheck.repository.CompanyRepository;

@Service
@AllArgsConstructor
public class CompanyService {

    private CompanyRepository companyRepository;

    private CompanyMapper companyMapper;

    public CompanyDto findCompanyByName(String name){
        return companyMapper.companyToCompanyDto(companyRepository.findCompanyByCompanyName(name));
    }
}
