package org.startup.onecheck.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.startup.onecheck.model.dto.CheckDto;
import org.startup.onecheck.model.dto.ProductDto;
import org.startup.onecheck.model.entity.Check;
import org.startup.onecheck.model.mapper.CheckMapper;
import org.startup.onecheck.repository.BasketRepository;
import org.startup.onecheck.repository.CheckRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckService {

    private CheckRepository checkRepository;

    private CheckMapper checkMapper;

    private BasketRepository basketRepository;

    public CheckDto findById(Long id){
        Optional<Check> optionalCheck = checkRepository.findById(id);
        return checkMapper.checkToCheckDto(optionalCheck.orElse(null));
    }

    public void save(CheckDto checkDto){
        Check check = checkMapper.checkDtoToCheck(checkDto);
        check.setBasket(basketRepository.findById(1L).orElse(null));
        checkRepository.save(check);
    }

    public void addProduct(ProductDto productDto){
        CheckDto findCheck = findById(1L);
        if(findCheck != null){
            findCheck.getProducts().add(productDto);
            save(findCheck);
        }
    }
}
