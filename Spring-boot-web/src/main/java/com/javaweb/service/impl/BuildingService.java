package com.javaweb.service.impl;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserService userService;

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
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        if(buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            buildingRepository.save(buildingEntity);
            List<RentAreaEntity> rentAreaEntities = new ArrayList<RentAreaEntity>();
            List<String> rentAreaInput = Arrays.asList(buildingDTO.getRentArea().split(",\\s*"));
            List<Long> rentAreaList = rentAreaInput.stream().map(Long::parseLong).collect(Collectors.toList());
            for (Long rentAreaValue : rentAreaList) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(rentAreaValue);
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaRepository.save(rentAreaEntity);
            }
        }
        else{
            buildingRepository.save(buildingEntity);
            }
    }



    @Override
    public void deleteByIdsIn(List <Long> ids) {
            List<BuildingEntity> buildingEntities = buildingRepository.findAllByIdIn(ids);
            assignmentBuildingRepository.deleteByBuildingsIn(buildingEntities);
            rentAreaRepository.deleteByBuildingIdIn(ids);
            buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public void assignBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findOneById(assignmentBuildingDTO.getBuildingId());
        assignmentBuildingRepository.deleteByBuildingsIs(buildingEntity);
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        for(UserEntity staff : staffs) {
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setStaffs(staff);
            assignmentBuildingEntity.setBuildings(buildingEntity);
            assignmentBuildingRepository.save(assignmentBuildingEntity);
        }
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItem();
    }

}
