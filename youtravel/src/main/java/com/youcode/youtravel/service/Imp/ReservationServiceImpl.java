package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.ReservationDTO;
import com.youcode.youtravel.dto.ResponseDto.ReservationDTOResp;
import com.youcode.youtravel.entities.Journey;
import com.youcode.youtravel.entities.Reservation;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.JourneyRepository;
import com.youcode.youtravel.repositories.ReservationRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.ReservationService;
import com.youcode.youtravel.utils.ReservationID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final JourneyRepository journeyRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private ReservationServiceImpl(ReservationRepository reservationRepository,
                                   JourneyRepository journeyRepository,
                                   UserRepository userRepository,
                                   ModelMapper modelMapper){
        this.reservationRepository = reservationRepository;
        this.journeyRepository = journeyRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ReservationDTOResp create(ReservationDTO reservationDTO){

            Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);

            Journey journey = journeyRepository.findById(reservationDTO.getJourney_id())
                    .orElseThrow(() -> new ResourceNotFoundException("not found journey with id : " + reservationDTO.getJourney_id()));
            reservation.setJourney(journey);

            if(reservationDTO.getReservedPlaces() <= journey.getNbrPlaces()){
                var nbr = journey.getNbrPlaces() - reservationDTO.getReservedPlaces();
                journey.setNbrPlaces(nbr);
                journeyRepository.save(journey);

                User user = userRepository.findById(reservationDTO.getUser_id())
                        .orElseThrow(() -> new ResourceNotFoundException("not found member with id : " + reservationDTO.getUser_id()));
                reservation.setUser(user);


                ReservationID reservationID = new ReservationID(
                        reservationDTO.getUser_id(),
                        reservationDTO.getJourney_id()
                );

                if (reservationRepository.existsById(reservationID)) {
                    throw new ResourceNotFoundException("Reservation already to the member");
                } else {
                    reservation.setReservationID(reservationID);
                }


                reservation = reservationRepository.save(reservation);
                return modelMapper.map(reservation, ReservationDTOResp.class);
            }
            else {
                throw new ResourceNotFoundException("All seats are full");

            }



    }


    @Override
    public void delete(Long uid, Long code){
        ReservationID reservationID = new ReservationID(
                uid,
                code
        );

        Reservation reservation = reservationRepository.findById(reservationID)
                .orElseThrow(()-> new ResourceNotFoundException("Reservation not found with id "+code));
        reservationRepository.delete(reservation);
    }

    @Override
    public ReservationDTOResp getOne(Long uid, Long code){
        ReservationID reservationID = new ReservationID(
                uid,
                code
        );
        Reservation reservation = reservationRepository.findById(reservationID)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id "+code));
        return modelMapper.map(reservation, ReservationDTOResp.class);
    }


    @Override
    public List<ReservationDTOResp> findAll() {
        return reservationRepository.findAll().stream().map(reservation -> modelMapper.map(reservation, ReservationDTOResp.class)).collect(Collectors.toList());
    }


    @Override
    public ReservationDTOResp update(Long uid, Long code, ReservationDTO reservationDTO) {
        ReservationID reservationID = new ReservationID(
                uid,
                code
        );
        Reservation existingRanking = reservationRepository.findById(reservationID)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Not found with this: " + code));
        return modelMapper.map(reservationRepository.save(existingRanking), ReservationDTOResp.class);
    }


    @Override
    public Page<ReservationDTOResp> findWithPagination(Pageable pageable) {
        Page<Reservation> rankingPage = reservationRepository.findAll(pageable);
        return rankingPage.map(reservation -> modelMapper.map(reservation, ReservationDTOResp.class));
    }



}
