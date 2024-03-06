package com.youcode.youtravel.service;

import com.youcode.youtravel.dto.ReservationDTO;
import com.youcode.youtravel.dto.ResponseDto.ReservationDTOResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    ReservationDTOResp create(ReservationDTO reservationDTO);
    void delete(Long uid, Long code);
    ReservationDTOResp getOne(Long uid, Long code);
    List<ReservationDTOResp> findAll();
    ReservationDTOResp update(Long uid, Long code, ReservationDTO reservationDTO);
    Page<ReservationDTOResp> findWithPagination(Pageable pageable);
}
