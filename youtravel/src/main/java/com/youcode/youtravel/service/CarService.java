package com.youcode.youtravel.service;


import com.youcode.youtravel.dto.CarDTO;
import com.youcode.youtravel.dto.ResponseDto.CarDTOResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarDTOResp create(CarDTO carDTO);
    void delete(Long id);
    CarDTOResp getOne(Long id);
    List<CarDTOResp> findAll();
    CarDTOResp update(Long id, CarDTO journeyDTO);
    Page<CarDTOResp> findWithPagination(Pageable pageable);
}
