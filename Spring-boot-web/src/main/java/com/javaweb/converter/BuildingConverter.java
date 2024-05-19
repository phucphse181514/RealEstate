package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingSearchResponse (BuildingEntity building) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(building, BuildingSearchResponse.class); // BuildingDTO.class generic class
        String districtName = districtCode.district().get(building.getDistrict());
        buildingSearchResponse.setAddress(building.getStreet() + ", " + building.getWard() + ", " + districtName);
        buildingSearchResponse.setEmptyArea(null);
        buildingSearchResponse.setBrokerageFee(null);
        String rentAreas = building.getRentAreas().stream().map(item -> item.getValue().toString()).collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentAreas);
        return buildingSearchResponse;
    }
}
