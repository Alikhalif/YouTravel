package com.youcode.youtravel.service;

import com.youcode.youtravel.dto.GroupDTO;
import com.youcode.youtravel.dto.ResponseDto.GroupDTOResp;
import com.youcode.youtravel.repositories.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService {
    GroupDTOResp create(GroupDTO groupDTO);
    void delete(Long id);
    GroupDTOResp getOne(Long id);
    List<GroupDTOResp> findAll();
    GroupDTOResp update(Long id, GroupDTO groupDTO);
    Page<GroupDTOResp> findWithPagination(Pageable pageable);
}
