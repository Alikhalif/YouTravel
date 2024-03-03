package com.youcode.youtravel.controller;

import com.youcode.youtravel.dto.GroupDTO;
import com.youcode.youtravel.dto.ResponseDto.GroupDTOResp;
import com.youcode.youtravel.service.GroupService;
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
@RequestMapping(path = "api/group", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    @Autowired
    private GroupService groupService;
    @PostMapping
    public ResponseEntity<GroupDTOResp> createFish(@Valid @RequestBody GroupDTO groupDTO) {
        GroupDTOResp groupDTOResp = groupService.create(groupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupDTOResp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteFish(@PathVariable Long id) {
        groupService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Group deleted successfully.");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTOResp> findFishByID(@PathVariable Long id) {
        GroupDTOResp group = groupService.getOne(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<GroupDTOResp>> getFishes() {
        List<GroupDTOResp> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTOResp> updateFish(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO) {
        GroupDTOResp updatedGroup = groupService.update(id, groupDTO);
        return ResponseEntity.ok(updatedGroup);
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<GroupDTOResp>> getPaginatedLevel(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(groupService.findWithPagination(pageable).getContent());
    }

}
