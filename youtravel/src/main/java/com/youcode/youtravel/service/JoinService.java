package com.youcode.youtravel.service;

import com.youcode.youtravel.dto.JoinDTO;
import com.youcode.youtravel.dto.ResponseDto.JoinDTOResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JoinService {
    JoinDTOResp create(JoinDTO joinDTO);
    void delete(Long uid, Long num);
    JoinDTOResp getOne(Long uid, Long num);
    List<JoinDTOResp> findAll();
    JoinDTOResp update(Long uid, Long num, JoinDTO joinDTO);
    Page<JoinDTOResp> findWithPagination(Pageable pageable);

}
