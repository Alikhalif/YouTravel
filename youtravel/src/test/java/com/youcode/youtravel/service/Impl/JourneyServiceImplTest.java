package com.youcode.youtravel.service.Impl;
import com.youcode.youtravel.dto.JourneyDTO;
import com.youcode.youtravel.dto.ResponseDto.JourneyDTOResp;
import com.youcode.youtravel.entities.Car;
import com.youcode.youtravel.entities.Journey;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.CarRepository;
import com.youcode.youtravel.repositories.JourneyRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.Imp.JourneyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JourneyServiceImplTest {
    @Mock
    private JourneyRepository journeyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private JourneyServiceImpl journeyService;

    private Journey journey;
    private JourneyDTO journeyDTO;
    private JourneyDTOResp journeyDTOResp;
    private User user;
    private Car car;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUid(1L);

        car = new Car();
        car.setId(1L);

        journey = new Journey();
        journey.setCode(1L);
        journey.setCountryStarting("Morocco");
        journey.setUser(user);
        journey.setCar(car);

        journeyDTO = new JourneyDTO();
        journeyDTO.setCountryStarting("Morocco");
        journeyDTO.setUser_id(1L);
        journeyDTO.setCar_id(1L);

        journeyDTOResp = new JourneyDTOResp();
        journeyDTOResp.setCode(1L);
        journeyDTOResp.setCountryStarting("Morocco");
    }

    @Test
    void create() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(journeyRepository.save(any(Journey.class))).thenReturn(journey);
        when(modelMapper.map(journeyDTO, Journey.class)).thenReturn(journey);
        when(modelMapper.map(journey, JourneyDTOResp.class)).thenReturn(journeyDTOResp);

        JourneyDTOResp result = journeyService.create(journeyDTO);

        assertEquals(journeyDTOResp, result);
        verify(journeyRepository, times(1)).save(journey);
    }

    @Test
    void delete() {
        when(journeyRepository.findById(1L)).thenReturn(Optional.of(journey));

        journeyService.delete(1L);

        verify(journeyRepository, times(1)).delete(journey);
    }

    @Test
    void getOne() {
        Long journeyId = 1L;
        Journey journey = new Journey();
        journey.setCode(journeyId);
        journey.setCountryStarting("Morocco");
        journey.setUser(user);
        journey.setCar(car);

        JourneyDTOResp expectedJourneyDTO = new JourneyDTOResp();
        expectedJourneyDTO.setCode(journeyId);
        expectedJourneyDTO.setCountryStarting("Morocco");

        when(journeyRepository.findById(journeyId)).thenReturn(Optional.of(journey));
        when(modelMapper.map(journey, JourneyDTOResp.class)).thenReturn(expectedJourneyDTO);

        JourneyDTOResp result = journeyService.getOne(journeyId);

        assertEquals(expectedJourneyDTO, result);
    }


    @Test
    void update() {
        when(journeyRepository.findById(1L)).thenReturn(Optional.of(journey));
        when(journeyRepository.save(journey)).thenReturn(journey);
        when(modelMapper.map(journey, JourneyDTOResp.class)).thenReturn(journeyDTOResp);

        JourneyDTOResp result = journeyService.update(1L, journeyDTO);

        assertEquals(journeyDTOResp, result);
        verify(journeyRepository, times(1)).save(journey);
    }

    @Test
    void updateNotFound() {
        when(journeyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> journeyService.update(1L, journeyDTO));
    }
}
