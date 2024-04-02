package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.PlaceGroupDTO;
import com.youcode.youtravel.dto.ResponseDto.PlaceGroupDTOResp;
import com.youcode.youtravel.entities.Group;
import com.youcode.youtravel.entities.PlacesGroup;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.GroupRepository;
import com.youcode.youtravel.repositories.PlacesGroupRepository;
import com.youcode.youtravel.service.PlacesGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacesGroupServiceImpl implements PlacesGroupService {
    private final PlacesGroupRepository placesGroupRepository;
    private final GroupRepository groupRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PlacesGroupServiceImpl(PlacesGroupRepository placesGroupRepository,
                                  GroupRepository groupRepository,
                                  ModelMapper modelMapper
                                  ){
        this.placesGroupRepository = placesGroupRepository;
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PlaceGroupDTOResp create(PlaceGroupDTO placeGroupDTO){
        PlacesGroup placesGroup = modelMapper.map(placeGroupDTO, PlacesGroup.class);

        Group group = groupRepository.findById(placeGroupDTO.getId_group())
                .orElseThrow(() -> new ResourceNotFoundException("not found Group with id : "+placeGroupDTO.getId_group()));
        placesGroup.setGroup(group);

        placesGroup = placesGroupRepository.save(placesGroup);
        return modelMapper.map(placesGroup, PlaceGroupDTOResp.class);
    }


    @Override
    public void delete(Long id){
        PlacesGroup placesGroup = placesGroupRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Place Group not found "));
        placesGroupRepository.delete(placesGroup);
    }


    @Override
    public PlaceGroupDTOResp getOne(Long id){
        PlacesGroup placesGroup = placesGroupRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Place Group not found "));
        return modelMapper.map(placesGroup, PlaceGroupDTOResp.class);
    }

    @Override
    public List<PlaceGroupDTOResp> findAll() {
        return placesGroupRepository.findAll().stream().map(place -> modelMapper.map(place, PlaceGroupDTOResp.class)).collect(Collectors.toList());
    }

    @Override
    public PlaceGroupDTOResp update(Long id, PlaceGroupDTO placeGroupDTO) {
        PlacesGroup placesGroup = placesGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Place Not found !"));
        return modelMapper.map(placesGroupRepository.save(placesGroup), PlaceGroupDTOResp.class);
    }







}
