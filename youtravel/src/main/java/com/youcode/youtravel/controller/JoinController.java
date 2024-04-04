package com.youcode.youtravel.controller;

import com.youcode.youtravel.dto.JoinDTO;
import com.youcode.youtravel.dto.ResponseDto.JoinDTOResp;
import com.youcode.youtravel.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/join")
public class JoinController {
    @Autowired
    private JoinService joinService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public ResponseEntity<JoinDTOResp> createJoin(@Valid @RequestBody JoinDTO joinDTO) {
        JoinDTOResp joinDTOResp = joinService.create(joinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(joinDTOResp);
    }


    @DeleteMapping("/{uid}/{num}")
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public ResponseEntity<Map<String, String>> deleteJoin(@PathVariable Long uid, @PathVariable Long num) {
        joinService.delete(uid,num);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Join deleted successfully.");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/{uid}/{num}")
    public ResponseEntity<JoinDTOResp> findJoinByID(@PathVariable Long uid, @PathVariable Long num) {
        JoinDTOResp join = joinService.getOne(uid, num);
        return ResponseEntity.ok(join);
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public ResponseEntity<List<JoinDTOResp>> getJoins() {
        List<JoinDTOResp> joins = joinService.findAll();
        return ResponseEntity.ok(joins);
    }

    @PutMapping("/{uid}/{num}")
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public ResponseEntity<JoinDTOResp> updateJoin(@PathVariable Long uid, @PathVariable Long num, @Valid @RequestBody JoinDTO joinDTO) {
        JoinDTOResp updatedJoin = joinService.update(uid, num, joinDTO);
        return ResponseEntity.ok(updatedJoin);
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<JoinDTOResp>> getPaginatedJoin(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(joinService.findWithPagination(pageable).getContent());
    }
}
