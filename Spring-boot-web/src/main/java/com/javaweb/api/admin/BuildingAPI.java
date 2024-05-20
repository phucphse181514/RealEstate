package com.javaweb.api.admin;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping( "/api/buildings")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userService;

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        //xuong DB them data
        buildingService.addOrUpdateBuilding(buildingDTO);
        return new String("Add or update building successfully!");
    }
    @DeleteMapping
    public String deleteBuilding(@RequestBody List<Long> ids){
        //xuong DB xoa data
        buildingService.deleteByIdsIn(ids);
        return new String("Delete building successfully!");
    }

    @GetMapping("{id}/staffs")
    public ResponseDTO loadStaffs (@PathVariable("id") Long id){
        return userService.listStaff(id);
    }

    @PutMapping
    public void assignBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.assignBuilding(assignmentBuildingDTO);
    }

}
