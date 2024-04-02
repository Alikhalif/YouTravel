package com.youcode.youtravel.service;

import com.youcode.youtravel.dto.PlaceGroupDTO;
import com.youcode.youtravel.dto.ResponseDto.PlaceGroupDTOResp;

import java.util.List;

public interface PlacesGroupService {
    PlaceGroupDTOResp create(PlaceGroupDTO placeGroupDTO);
    void delete(Long id);
    PlaceGroupDTOResp getOne(Long id);
    List<PlaceGroupDTOResp> findAll();
    PlaceGroupDTOResp update(Long id, PlaceGroupDTO placeGroupDTO);
}
