package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private UserRepository userRepository;

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

    public BuildingEntity toBuildingEntity (BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        if(buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            List<RentAreaEntity> rentAreaEntities = new ArrayList<RentAreaEntity>();
            List<String> rentAreaInput = Arrays.asList(buildingDTO.getRentArea().split(",\\s*"));
            List<Long> rentAreaList = rentAreaInput.stream().map(Long::parseLong).collect(Collectors.toList());
            for (Long rentAreaValue : rentAreaList) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(rentAreaValue);
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaEntities.add(rentAreaEntity);
            }
            buildingEntity.setRentAreas(rentAreaEntities);
        }
        if(buildingDTO.getId() != null){
            List<UserEntity> userEntities = userRepository.findByBuildingIdIs(buildingDTO.getId());
            buildingEntity.setUser(userEntities);
        }
        return buildingEntity;
    }
}
