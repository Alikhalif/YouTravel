package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.JoinDTO;
import com.youcode.youtravel.dto.ReservationDTO;
import com.youcode.youtravel.dto.ResponseDto.JoinDTOResp;
import com.youcode.youtravel.dto.ResponseDto.ReservationDTOResp;
import com.youcode.youtravel.entities.*;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.*;
import com.youcode.youtravel.service.JoinService;
import com.youcode.youtravel.utils.JoinID;
import com.youcode.youtravel.utils.ReservationID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoinServiceImpl implements JoinService {
    private final JoinRepository joinRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private JoinServiceImpl(JoinRepository joinRepository,
                            GroupRepository groupRepository,
                            UserRepository userRepository,
                            ModelMapper modelMapper){
        this.joinRepository = joinRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public JoinDTOResp create(JoinDTO joinDTO){

        Join join = modelMapper.map(joinDTO, Join.class);

        Group group = groupRepository.findById(joinDTO.getGroup_id())
                .orElseThrow(() -> new ResourceNotFoundException("not found group with id : " + joinDTO.getGroup_id()));
        join.setGroup(group);


        User user = userRepository.findById(joinDTO.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("not found member with id : " + joinDTO.getUser_id()));
        join.setUser(user);


        JoinID joinID = new JoinID(
                joinDTO.getUser_id(),
                joinDTO.getGroup_id()
        );

        if (joinRepository.existsById(joinID)) {
            throw new ResourceNotFoundException("Join already to the member");
        } else {
            join.setJoinID(joinID);
        }


        join = joinRepository.save(join);
        return modelMapper.map(join, JoinDTOResp.class);
    }


    @Override
    public void delete(Long uid, Long num){
        JoinID joinID = new JoinID(
                uid,
                num
        );

        Join join = joinRepository.findById(joinID)
                .orElseThrow(()-> new ResourceNotFoundException("Join not found with id "+num));
        joinRepository.delete(join);
    }


    public JoinDTOResp getOne(Long uid, Long num){
        JoinID joinID = new JoinID(
                uid,
                num
        );
        Join join = joinRepository.findById(joinID)
                .orElseThrow(()-> new ResourceNotFoundException("Join not found with id "+num));
        return modelMapper.map(join, JoinDTOResp.class);
    }


    public List<JoinDTOResp> findAll() {
        return joinRepository.findAll().stream().map(join -> modelMapper.map(join, JoinDTOResp.class)).collect(Collectors.toList());
    }


    public JoinDTOResp update(Long uid, Long num, JoinDTO joinDTO) {
        JoinID joinID = new JoinID(
                uid,
                num
        );
        Join existingJoin = joinRepository.findById(joinID)
                .orElseThrow(()-> new ResourceNotFoundException("Join not found with id "+num));
        return modelMapper.map(joinRepository.save(existingJoin), JoinDTOResp.class);
    }

    @Override
    public Page<JoinDTOResp> findWithPagination(Pageable pageable) {
        Page<Join> joinPage = joinRepository.findAll(pageable);
        return joinPage.map(join -> modelMapper.map(join, JoinDTOResp.class));
    }
}
