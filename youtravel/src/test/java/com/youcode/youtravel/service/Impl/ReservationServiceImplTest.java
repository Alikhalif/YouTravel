package com.youcode.youtravel.service.Impl;

import com.youcode.youtravel.dto.ReservationDTO;
import com.youcode.youtravel.dto.ResponseDto.ReservationDTOResp;
import com.youcode.youtravel.entities.Journey;
import com.youcode.youtravel.entities.Reservation;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.JourneyRepository;
import com.youcode.youtravel.repositories.ReservationRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.Imp.ReservationServiceImpl;
import com.youcode.youtravel.utils.ReservationID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {
    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private JourneyRepository journeyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private User user;
    private Journey journey;
    private Reservation reservation;
    private ReservationDTO reservationDTO;
    private ReservationDTOResp reservationDTOResp;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUid(1L);

        journey = new Journey();
        journey.setCode(1L);
        journey.setNbrPlaces(10);

        reservation = new Reservation();
        reservation.setReservationID(new ReservationID(1L, 1L));
        reservation.setReservedPlaces(2);
        reservation.setUser(user);
        reservation.setJourney(journey);

        reservationDTO = new ReservationDTO();
        reservationDTO.setReservedPlaces(2);
        reservationDTO.setUser_id(1L);
        reservationDTO.setJourney_id(1L);

        reservationDTOResp = new ReservationDTOResp();
        reservationDTOResp.setReservedPlaces(2);
//        reservationDTOResp.setUser_id(1L);
//        reservationDTOResp.setJourney_id(1L);
    }

    @Test
    void testCreate() {
        when(journeyRepository.findById(reservationDTO.getJourney_id())).thenReturn(Optional.of(journey));
        when(userRepository.findById(reservationDTO.getUser_id())).thenReturn(Optional.of(user));
        when(reservationRepository.existsById(reservation.getReservationID())).thenReturn(false);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        when(modelMapper.map(reservationDTO, Reservation.class)).thenReturn(reservation);
        when(modelMapper.map(reservation, ReservationDTOResp.class)).thenReturn(reservationDTOResp);

        ReservationDTOResp result = reservationService.create(reservationDTO);

        assertEquals(reservationDTOResp, result);
        verify(journeyRepository, times(1)).findById(reservationDTO.getJourney_id());
        verify(userRepository, times(1)).findById(reservationDTO.getUser_id());
        verify(reservationRepository, times(1)).existsById(reservation.getReservationID());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(modelMapper, times(1)).map(reservationDTO, Reservation.class);
        verify(modelMapper, times(1)).map(reservation, ReservationDTOResp.class);
    }

    // Add more test cases for other scenarios and methods

    @Test
    void testDelete() {
        ReservationID reservationID = new ReservationID(1L, 1L);
        when(reservationRepository.findById(reservationID)).thenReturn(Optional.of(reservation));

        reservationService.delete(1L, 1L);

        verify(reservationRepository, times(1)).findById(reservationID);
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testDeleteReservationNotFound() {
        ReservationID reservationID = new ReservationID(1L, 1L);
        when(reservationRepository.findById(reservationID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservationService.delete(1L, 1L));
        verify(reservationRepository, times(1)).findById(reservationID);
        verify(reservationRepository, never()).delete(any());
    }

    @Test
    void testGetOne() {
        ReservationID reservationID = new ReservationID(1L, 1L);
        when(reservationRepository.findById(reservationID)).thenReturn(Optional.of(reservation));
        when(modelMapper.map(reservation, ReservationDTOResp.class)).thenReturn(reservationDTOResp);

        ReservationDTOResp result = reservationService.getOne(1L, 1L);

        assertEquals(reservationDTOResp, result);
        verify(reservationRepository, times(1)).findById(reservationID);
        verify(modelMapper, times(1)).map(reservation, ReservationDTOResp.class);
    }

    @Test
    void testGetOneReservationNotFound() {
        ReservationID reservationID = new ReservationID(1L, 1L);
        when(reservationRepository.findById(reservationID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservationService.getOne(1L, 1L));
        verify(reservationRepository, times(1)).findById(reservationID);
        verify(modelMapper, never()).map(any(), any());
    }

    @Test
    void testFindAll() {
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);
        when(modelMapper.map(reservation, ReservationDTOResp.class)).thenReturn(reservationDTOResp);

        List<ReservationDTOResp> result = reservationService.findAll();

        assertEquals(1, result.size());
        assertEquals(reservationDTOResp, result.get(0));
        verify(reservationRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(reservation, ReservationDTOResp.class);
    }

    @Test
    void testUpdate() {
        ReservationID reservationID = new ReservationID(1L, 1L);
        when(reservationRepository.findById(reservationID)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(modelMapper.map(reservation, ReservationDTOResp.class)).thenReturn(reservationDTOResp);

        ReservationDTOResp result = reservationService.update(1L, 1L, reservationDTO);

        assertEquals(reservationDTOResp, result);
        verify(reservationRepository, times(1)).findById(reservationID);
        verify(reservationRepository, times(1)).save(reservation);
        verify(modelMapper, times(1)).map(reservation, ReservationDTOResp.class);
    }
}
