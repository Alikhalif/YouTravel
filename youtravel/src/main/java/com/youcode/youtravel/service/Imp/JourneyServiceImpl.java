package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.JourneyDTO;
import com.youcode.youtravel.dto.JourneySearchDTO;
import com.youcode.youtravel.dto.ResponseDto.CarDTOResp;
import com.youcode.youtravel.dto.ResponseDto.JourneyDTOResp;
import com.youcode.youtravel.dto.ResponseDto.UserDTOResp;
import com.youcode.youtravel.entities.Car;
import com.youcode.youtravel.entities.Journey;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.CarRepository;
import com.youcode.youtravel.repositories.JourneyRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.JourneyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JourneyServiceImpl implements JourneyService {

    private final JourneyRepository journeyRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public JourneyServiceImpl(JourneyRepository journeyRepository, UserRepository userRepository, CarRepository carRepository, ModelMapper modelMapper){
        this.journeyRepository = journeyRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }


    @Override
    public JourneyDTOResp create(JourneyDTO journeyDTO){
        Journey journey = modelMapper.map(journeyDTO, Journey.class);

        User user = userRepository.findById(journeyDTO.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("not found User with id : "+journeyDTO.getUser_id()));
        journey.setUser(user);

        Car car = carRepository.findById(journeyDTO.getCar_id())
                .orElseThrow(() -> new ResourceNotFoundException("not found car with id : "+journeyDTO.getCar_id()));
        journey.setCar(car);

        journey = journeyRepository.save(journey);
        return modelMapper.map(journey, JourneyDTOResp.class);
    }

    @Override
    public void delete(Long id){
        Journey journey = journeyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Group not found "));
        journeyRepository.delete(journey);
    }

    @Override
    public JourneyDTOResp getOne(Long id){
        Optional<Journey> journey = journeyRepository.findById(id);
        if (journey == null) {
            throw new ResourceNotFoundException("Fish not found");
        }
        return modelMapper.map(journey, JourneyDTOResp.class);
    }


    @Override
    public List<JourneyDTOResp> findAll() {
        return journeyRepository.findAll().stream()
                .map(journey -> {
                    JourneyDTOResp journeyDTO = modelMapper.map(journey, JourneyDTOResp.class);
                    journeyDTO.setCarDTOResp(modelMapper.map(carRepository.findById(journey.getCar().getId()), CarDTOResp.class));
                    journeyDTO.setUserDTOResp(modelMapper.map(userRepository.findById(journey.getUser().getUid()), UserDTOResp.class));
                    return journeyDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<JourneyDTOResp> findJourneysByCriteria(JourneySearchDTO searchDTO) {

        return journeyRepository.findJourneysByCriteria(searchDTO).stream()
                .map(journey -> {
                    JourneyDTOResp journeyDTO = modelMapper.map(journey, JourneyDTOResp.class);
                    journeyDTO.setCarDTOResp(modelMapper.map(carRepository.findById(journey.getCar().getId()), CarDTOResp.class));
                    journeyDTO.setUserDTOResp(modelMapper.map(userRepository.findById(journey.getUser().getUid()), UserDTOResp.class));
                    return journeyDTO;

                })
                .collect(Collectors.toList());
    }

    @Override
    public JourneyDTOResp update(Long id, JourneyDTO journeyDTO) {
        Journey journey = journeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fish Not found !"));
        return modelMapper.map(journeyRepository.save(journey), JourneyDTOResp.class);
    }

    @Override
    public Page<JourneyDTOResp> findWithPagination(Pageable pageable) {
        Page<Journey> journeyPage = journeyRepository.findAll(pageable);
        return journeyPage.map(journey -> modelMapper.map(journey, JourneyDTOResp.class));
    }
}
