package com.youcode.youtravel.service;

import com.youcode.youtravel.dto.PlaceGroupDTO;
import com.youcode.youtravel.dto.PlaceJourneyDTO;
import com.youcode.youtravel.dto.ResponseDto.PlaceJourneyDTOResp;

import java.util.List;

public interface PlacesJourneyService {
    PlaceJourneyDTOResp create(PlaceJourneyDTO placeJourneyDTO);
    void delete(Long id);
    PlaceJourneyDTOResp getOne(Long id);
    List<PlaceJourneyDTOResp> findAll();
    PlaceJourneyDTOResp update(Long id, PlaceGroupDTO placeGroupDTO);
}
