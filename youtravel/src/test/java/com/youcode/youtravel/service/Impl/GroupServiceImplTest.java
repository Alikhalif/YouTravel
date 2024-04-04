package com.youcode.youtravel.service.Impl;

import com.youcode.youtravel.dto.GroupDTO;
import com.youcode.youtravel.dto.ResponseDto.GroupDTOResp;
import com.youcode.youtravel.dto.ResponseDto.UserDTOResp;
import com.youcode.youtravel.entities.Group;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.GroupRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.Imp.GroupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTest {
    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GroupServiceImpl groupService;

    private User user;
    private Group group;
    private GroupDTO groupDTO;
    private GroupDTOResp groupDTOResp;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUid(1L);

        group = new Group();
        group.setNum(1L);
        group.setUser(user);

        groupDTO = new GroupDTO();
        groupDTO.setUser_id(1L);

        groupDTOResp = new GroupDTOResp();
        groupDTOResp.setNum(1L);
    }

    @Test
    void testCreate() {
        when(userRepository.findById(groupDTO.getUser_id())).thenReturn(Optional.of(user));
        when(groupRepository.save(any(Group.class))).thenReturn(group);
        when(modelMapper.map(groupDTO, Group.class)).thenReturn(group);
        when(modelMapper.map(group, GroupDTOResp.class)).thenReturn(groupDTOResp);

        GroupDTOResp result = groupService.create(groupDTO);

        assertEquals(groupDTOResp, result);
        verify(userRepository, times(1)).findById(groupDTO.getUser_id());
        verify(groupRepository, times(1)).save(any(Group.class));
        verify(modelMapper, times(1)).map(groupDTO, Group.class);
        verify(modelMapper, times(1)).map(group, GroupDTOResp.class);
    }

    @Test
    void testCreateUserNotFound() {
        when(userRepository.findById(groupDTO.getUser_id())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> groupService.create(groupDTO));
        verify(userRepository, times(1)).findById(groupDTO.getUser_id());
        verify(groupRepository, never()).save(any(Group.class));
    }

    @Test
    void testDelete() {
        when(groupRepository.findById(group.getNum())).thenReturn(Optional.of(group));

        groupService.delete(group.getNum());

        verify(groupRepository, times(1)).findById(group.getNum());
        verify(groupRepository, times(1)).delete(group);
    }

    @Test
    void testDeleteGroupNotFound() {
        when(groupRepository.findById(group.getNum())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> groupService.delete(group.getNum()));
        verify(groupRepository, times(1)).findById(group.getNum());
        verify(groupRepository, never()).delete(any(Group.class));
    }



    @Test
    void testGetGroupsByAuthenticatedUser() {
        List<Group> groups = Arrays.asList(group);
        when(groupRepository.findByUser(user)).thenReturn(groups);
        when(modelMapper.map(group, GroupDTOResp.class)).thenReturn(groupDTOResp);

        List<GroupDTOResp> result = groupService.getGroupsByAuthenticatedUser(user);

        assertEquals(1, result.size());
        assertEquals(groupDTOResp, result.get(0));
        verify(groupRepository, times(1)).findByUser(user);
        verify(modelMapper, times(1)).map(group, GroupDTOResp.class);
    }


    @Test
    void testUpdate() {
        when(groupRepository.findById(group.getNum())).thenReturn(Optional.of(group));
        when(groupRepository.save(group)).thenReturn(group);
        when(modelMapper.map(group, GroupDTOResp.class)).thenReturn(groupDTOResp);

        GroupDTOResp result = groupService.update(group.getNum(), groupDTO);

        assertEquals(groupDTOResp, result);
        verify(groupRepository, times(1)).findById(group.getNum());
        verify(groupRepository, times(1)).save(group);
        verify(modelMapper, times(1)).map(group, GroupDTOResp.class);
    }

    @Test
    void testUpdateGroupNotFound() {
        when(groupRepository.findById(group.getNum())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> groupService.update(group.getNum(), groupDTO));
        verify(groupRepository, times(1)).findById(group.getNum());
        verify(groupRepository, never()).save(any(Group.class));
        verify(modelMapper, never()).map(any(), any());
    }

}
