package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController (value="buildingControllerOfAdmin")

public class BuildingController {
    @Autowired
    private IBuildingService buildingService;

    @Autowired
    IUserService userService;
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value="/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequest buildingSearchRequest){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("staffs",userService.getStaffs());
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        //Xuong DB lay data len
        List<BuildingSearchResponse> result = buildingService.findAll(buildingSearchRequest);
        mav.addObject("buildings", result);
        return mav;
    }
    @GetMapping(value="/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        return mav;
    }
    @GetMapping(value="/admin/building-edit-{id}")
    public ModelAndView updateBuilding(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingEntity buildingEntity = buildingRepository.findOneById(id);
        List<String> typeCodeList = Arrays.asList(buildingEntity.getType().split(",\\s*"));
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setTypeCode(typeCodeList);
        if(!buildingEntity.getRentAreas().isEmpty()){
            String rentArea = "";
            int count = 0;
            for(RentAreaEntity rentAreaEntity : buildingEntity.getRentAreas()){
                rentArea += rentAreaEntity.getValue().toString();
                count++;
                if(count < buildingEntity.getRentAreas().size()){
                    rentArea += ", ";
                }
            }
            buildingDTO.setRentArea(rentArea);
        }

        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        //findByBuildingId
        return mav;
    }

}
