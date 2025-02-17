package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping( "/api/buildings")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;
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


}
