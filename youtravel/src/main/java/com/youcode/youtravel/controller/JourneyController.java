package com.youcode.youtravel.controller;

import com.youcode.youtravel.dto.JourneyDTO;
import com.youcode.youtravel.dto.ResponseDto.JourneyDTOResp;
import com.youcode.youtravel.service.JourneyService;
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
@RequestMapping(path = "api/journey", produces = MediaType.APPLICATION_JSON_VALUE)
public class JourneyController {
    @Autowired
    private JourneyService journeyService;

    @PostMapping
    public ResponseEntity<JourneyDTOResp> createFish(@Valid @RequestBody JourneyDTO journeyDTO) {
        JourneyDTOResp journeyDTOResp = journeyService.create(journeyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(journeyDTOResp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteFish(@PathVariable Long id) {
        journeyService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Journey deleted successfully.");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JourneyDTOResp> findFishByID(@PathVariable Long id) {
        JourneyDTOResp journey = journeyService.getOne(id);
        return ResponseEntity.ok(journey);
    }

    @GetMapping
    public ResponseEntity<List<JourneyDTOResp>> getFishes() {
        List<JourneyDTOResp> journeys = journeyService.findAll();
        return ResponseEntity.ok(journeys);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JourneyDTOResp> updateFish(@PathVariable Long id, @Valid @RequestBody JourneyDTO journeyDTO) {
        JourneyDTOResp updatedJourny = journeyService.update(id, journeyDTO);
        return ResponseEntity.ok(updatedJourny);
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<JourneyDTOResp>> getPaginatedLevel(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(journeyService.findWithPagination(pageable).getContent());
    }
}
