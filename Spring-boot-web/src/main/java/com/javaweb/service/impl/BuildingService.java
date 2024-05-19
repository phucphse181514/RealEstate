package com.javaweb.service.impl;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.service.IBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildingConverter buildingConverter	;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<BuildingSearchResponse>();
        for(BuildingEntity building : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.toBuildingSearchResponse(building);
            result.add(buildingSearchResponse);
        }
        return result;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        List<RentAreaEntity> rentAreaEntities = new ArrayList<RentAreaEntity>();
        List <String> rentAreaInput = Arrays.asList(buildingDTO.getRentArea().split(",\\s*"));
        List<Long> rentAreaList = rentAreaInput.stream().map(Long::parseLong).collect(Collectors.toList());
        for(Long rentAreaValue : rentAreaList) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(rentAreaValue);
            rentAreaEntities.add(rentAreaEntity);
        }

        buildingEntity.setRentAreas(rentAreaEntities);
        buildingRepository.save(buildingEntity);
        for(RentAreaEntity rentAreaEntity : rentAreaEntities) {
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaRepository.save(rentAreaEntity);
        }

    }



    @Override
    public void deleteByIdsIn(List <Long> ids) {
            rentAreaRepository.deleteByBuildingIdIn(ids);
            buildingRepository.deleteByIdIn(ids);
    }

}
