package com.javaweb.controller.admin;



import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController (value="buildingControllerOfAdmin")

public class BuildingController {
    @Autowired
    private IBuildingService buildingService;

    @Autowired
    IUserService userService;
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
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        buildingDTO.setName("DEV Building");
        buildingDTO.setManagerName("Nguyen Van A");
        buildingDTO.setManagerPhone("0703098260");
        buildingDTO.setBrokerageFee(3.2);
        List<String> typeCodeList = new ArrayList<>();
        typeCodeList.add("NGUYEN_CAN");
        buildingDTO.setTypeCode(typeCodeList);
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        //findByBuildingId
        return mav;
    }

}
