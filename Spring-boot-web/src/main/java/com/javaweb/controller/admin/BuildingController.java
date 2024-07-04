package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private MessageUtils messageUtil;

    @GetMapping(value="/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(SystemConstant.MODEL) BuildingSearchRequest model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        DisplayTagUtils.of(request, model);
        mav.addObject("staffs",userService.getStaffs());
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        //Xuong DB lay data len
        List<BuildingSearchResponse> result = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            model.setStaffId(staffId);
            result = buildingService.findAll(model);
        }
        else{
            result = buildingService.findAll(model);
        }
        model.setListResult(result);
        model.setTotalItems(buildingService.countTotalItems(model));
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
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
        buildingDTO.setImage(buildingEntity.getAvatar());
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
    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
