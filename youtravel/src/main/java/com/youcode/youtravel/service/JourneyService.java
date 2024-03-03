package com.youcode.youtravel.service;

import com.youcode.youtravel.dto.JourneyDTO;
import com.youcode.youtravel.dto.ResponseDto.JourneyDTOResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JourneyService {
    JourneyDTOResp create(JourneyDTO journeyDTO);
    void delete(Long id);
    JourneyDTOResp getOne(Long id);
    List<JourneyDTOResp> findAll();
    JourneyDTOResp update(Long id, JourneyDTO journeyDTO);
    Page<JourneyDTOResp> findWithPagination(Pageable pageable);
}
