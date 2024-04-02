package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.GroupDTO;
import com.youcode.youtravel.dto.ResponseDto.CarDTOResp;
import com.youcode.youtravel.dto.ResponseDto.GroupDTOResp;
import com.youcode.youtravel.dto.ResponseDto.JourneyDTOResp;
import com.youcode.youtravel.dto.ResponseDto.UserDTOResp;
import com.youcode.youtravel.entities.Group;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.GroupRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.GroupService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepsitory;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public GroupDTOResp create(GroupDTO groupDTO){
        Group group = modelMapper.map(groupDTO, Group.class);

        User user = userRepository.findById(groupDTO.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("not found User with id : "+groupDTO.getUser_id()));
        group.setUser(user);

        group = groupRepsitory.save(group);
        return modelMapper.map(group, GroupDTOResp.class);
    }

    @Override
    public void delete(Long id){
        Group group = groupRepsitory.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Group not found "));
        groupRepsitory.delete(group);
    }

    @Override
    public GroupDTOResp getOne(Long id){
        Optional<Group> group = groupRepsitory.findById(id);
        if (group == null) {
            throw new ResourceNotFoundException("Fish not found");
        }
        return modelMapper.map(group, GroupDTOResp.class);
    }

    @Override
    public List<GroupDTOResp> getGroupsByAuthenticatedUser(User authenticatedUser) {
        return groupRepsitory.findByUser(authenticatedUser).stream().map(group -> {
                 GroupDTOResp groupDTOResp = modelMapper.map(group, GroupDTOResp.class);
                 return groupDTOResp;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<GroupDTOResp> findAll() {
        return groupRepsitory.findAll().stream().map(group -> {
                    GroupDTOResp groupDTOResp = modelMapper.map(group, GroupDTOResp.class);
                    groupDTOResp.setUserDTOResp(modelMapper.map(userRepository.findById(group.getUser().getUid()), UserDTOResp.class));
                    return groupDTOResp;
                })
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTOResp update(Long id, GroupDTO groupDTO) {
        Group fish = groupRepsitory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fish Not found"));
        return modelMapper.map(groupRepsitory.save(fish), GroupDTOResp.class);
    }

    @Override
    public Page<GroupDTOResp> findWithPagination(Pageable pageable) {
        Page<Group> fishPage = groupRepsitory.findAll(pageable);
        return fishPage.map(group -> modelMapper.map(group, GroupDTOResp.class));
    }

}
