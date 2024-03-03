package com.youcode.youtravel.controller;

import com.youcode.youtravel.dto.CarDTO;
import com.youcode.youtravel.dto.ResponseDto.CarDTOResp;
import com.youcode.youtravel.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/car", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<CarDTOResp> createCar(@Valid @RequestBody CarDTO carDTO) {
        CarDTOResp carDTOResp = carService.create(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(carDTOResp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCar(@PathVariable Long id) {
        carService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Car deleted successfully.");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTOResp> findCarByID(@PathVariable Long id) {
        CarDTOResp car = carService.getOne(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping
    public ResponseEntity<List<CarDTOResp>> getCars() {
        List<CarDTOResp> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTOResp> updateFish(@PathVariable Long id, @Valid @RequestBody CarDTO carDTO) {
        CarDTOResp updatedCar = carService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<CarDTOResp>> getPaginatedLevel(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(carService.findWithPagination(pageable).getContent());
    }
}
