package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.PlaceGroupDTO;
import com.youcode.youtravel.dto.PlaceJourneyDTO;
import com.youcode.youtravel.dto.ResponseDto.PlaceJourneyDTOResp;
import com.youcode.youtravel.entities.Journey;
import com.youcode.youtravel.entities.PlacesJourney;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.*;
import com.youcode.youtravel.service.PlacesJourneyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacesJourneyServiceImpl implements PlacesJourneyService {
    private final PlacesJourneyRepository placesJourneyRepository;
    private final JourneyRepository journeyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PlacesJourneyServiceImpl(PlacesJourneyRepository placesJourneyRepository,
                                  JourneyRepository journeyRepository,
                                  ModelMapper modelMapper
    ){
        this.placesJourneyRepository = placesJourneyRepository;
        this.journeyRepository = journeyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PlaceJourneyDTOResp create(PlaceJourneyDTO placeJourneyDTO){
        PlacesJourney placesJourney = modelMapper.map(placeJourneyDTO, PlacesJourney.class);

        Journey journey = journeyRepository.findById(placeJourneyDTO.getId_journey())
                .orElseThrow(() -> new ResourceNotFoundException("not found journey with id : "+placeJourneyDTO.getId_journey()));
        placesJourney.setJourney(journey);

        placesJourney = placesJourneyRepository.save(placesJourney);
        return modelMapper.map(placesJourney, PlaceJourneyDTOResp.class);
    }


    @Override
    public void delete(Long id){
        PlacesJourney placesJourney = placesJourneyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Place journey not found "));
        placesJourneyRepository.delete(placesJourney);
    }


    @Override
    public PlaceJourneyDTOResp getOne(Long id){
        PlacesJourney placesJourney = placesJourneyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Place Journey not found "));
        return modelMapper.map(placesJourney, PlaceJourneyDTOResp.class);
    }

    @Override
    public List<PlaceJourneyDTOResp> findAll() {
        return placesJourneyRepository.findAll().stream().map(place -> modelMapper.map(place, PlaceJourneyDTOResp.class)).collect(Collectors.toList());
    }

    @Override
    public PlaceJourneyDTOResp update(Long id, PlaceGroupDTO placeGroupDTO) {
        PlacesJourney placesJourney = placesJourneyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Place Not found !"));
        return modelMapper.map(placesJourneyRepository.save(placesJourney), PlaceJourneyDTOResp.class);
    }

}
