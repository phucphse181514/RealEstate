package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;

import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest);
}
