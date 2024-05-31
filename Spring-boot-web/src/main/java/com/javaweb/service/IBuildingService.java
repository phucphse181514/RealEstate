package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    void addOrUpdateBuilding(BuildingDTO buildingDTO);

    void deleteByIdsIn(List<Long> ids);

    void assignBuilding(AssignmentBuildingDTO assignmentBuildingDTO);

    int countTotalItems(BuildingSearchRequest buildingSearchRequest);

    void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity);
}
