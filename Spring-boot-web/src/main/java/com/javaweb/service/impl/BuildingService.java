package com.javaweb.service.impl;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
//import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {


    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter	;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadFileUtils uploadFileUtils;

//    @Autowired
//    private AssignmentBuildingRepository assignmentBuildingRepository;
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
        saveThumbnail(buildingDTO,buildingEntity);
            buildingRepository.save(buildingEntity);
    }



    @Override
    public void deleteByIdsIn(List <Long> ids) {
//            List<BuildingEntity> buildingEntities = buildingRepository.findAllByIdIn(ids);
//            assignmentBuildingRepository.deleteByBuildingsIn(buildingEntities);
//            rentAreaRepository.deleteByBuildingIdIn(ids);
            buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public void assignBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findOneById(assignmentBuildingDTO.getBuildingId());
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        buildingEntity.setUser(staffs);
        buildingRepository.save(buildingEntity);
    }
    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItem(buildingSearchRequest);
    }

    @Override
    public void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
                    File file = new File("C://home/office" + buildingEntity.getAvatar());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }

}
