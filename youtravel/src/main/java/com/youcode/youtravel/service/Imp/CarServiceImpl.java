package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.CarDTO;
import com.youcode.youtravel.dto.ResponseDto.CarDTOResp;
import com.youcode.youtravel.entities.Car;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.CarRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, UserRepository userRepository, ModelMapper modelMapper){
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CarDTOResp create(CarDTO carDTO){
        Car car = modelMapper.map(carDTO, Car.class);

        User user = userRepository.findById(carDTO.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("not found User with id : "+carDTO.getUser_id()));
        car.setUser(user);

        car = carRepository.save(car);
        return modelMapper.map(car, CarDTOResp.class);
    }

    @Override
    public void delete(Long id){
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car not found "));
        carRepository.delete(car);
    }

    @Override
    public CarDTOResp getOne(Long id){
        Optional<Car> car = carRepository.findById(id);
        if (car == null) {
            throw new ResourceNotFoundException("Car not found");
        }
        return modelMapper.map(car, CarDTOResp.class);
    }


    @Override
    public List<CarDTOResp> findAll() {
        return carRepository.findAll().stream().map(car -> modelMapper.map(car, CarDTOResp.class)).collect(Collectors.toList());
    }

    @Override
    public CarDTOResp update(Long id, CarDTO journeyDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car Not found !"));
        return modelMapper.map(carRepository.save(car), CarDTOResp.class);
    }

    @Override
    public Page<CarDTOResp> findWithPagination(Pageable pageable) {
        Page<Car> carPage = carRepository.findAll(pageable);
        return carPage.map(car -> modelMapper.map(car, CarDTOResp.class));
    }
}
