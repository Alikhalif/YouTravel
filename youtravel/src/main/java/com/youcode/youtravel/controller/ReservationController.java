package com.youcode.youtravel.controller;

import com.youcode.youtravel.dto.JourneyDTO;
import com.youcode.youtravel.dto.ReservationDTO;
import com.youcode.youtravel.dto.ResponseDto.JourneyDTOResp;
import com.youcode.youtravel.dto.ResponseDto.ReservationDTOResp;
import com.youcode.youtravel.service.JourneyService;
import com.youcode.youtravel.service.ReservationService;
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
@RequestMapping(path = "api/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTOResp> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTOResp reservationDTOResp = reservationService.create(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationDTOResp);
    }

    @DeleteMapping("/{uid}/{code}")
    public ResponseEntity<Map<String, String>> deleteReservation(@PathVariable Long uid, @PathVariable Long code) {
        reservationService.delete(uid,code);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Reservation deleted successfully.");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/{uid}/{code}")
    public ResponseEntity<ReservationDTOResp> findReservationByID(@PathVariable Long uid, @PathVariable Long code) {
        ReservationDTOResp reservation = reservationService.getOne(uid, code);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTOResp>> getReservations() {
        List<ReservationDTOResp> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{uid}/{code}")
    public ResponseEntity<ReservationDTOResp> updateReservation(@PathVariable Long uid, @PathVariable Long code, @Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTOResp updatedReservation = reservationService.update(uid, code, reservationDTO);
        return ResponseEntity.ok(updatedReservation);
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<ReservationDTOResp>> getPaginatedReservation(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(reservationService.findWithPagination(pageable).getContent());
    }
}
