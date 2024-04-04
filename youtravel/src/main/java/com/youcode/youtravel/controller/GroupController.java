package com.youcode.youtravel.controller;

import com.youcode.youtravel.dto.GroupDTO;
import com.youcode.youtravel.dto.ResponseDto.GroupDTOResp;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public ResponseEntity<GroupDTOResp> createGroup(@Valid @RequestBody GroupDTO groupDTO) {
        GroupDTOResp groupDTOResp = groupService.create(groupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupDTOResp);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public ResponseEntity<Map<String, String>> deleteGroup(@PathVariable Long id) {
        groupService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Group deleted successfully.");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTOResp> findGroupByID(@PathVariable Long id) {
        GroupDTOResp group = groupService.getOne(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/user_group")
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public List<GroupDTOResp> getGroupsByAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User authenticatedUser = (User) authentication.getPrincipal();
            System.out.println(authenticatedUser);

            return groupService.getGroupsByAuthenticatedUser(authenticatedUser);
        }else {
            System.out.println("null");
            return groupService.getGroupsByAuthenticatedUser(null);
        }

    }

    @GetMapping
    public ResponseEntity<List<GroupDTOResp>> getGroups() {
        List<GroupDTOResp> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','BASE_USER')")
    public ResponseEntity<GroupDTOResp> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO) {
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
