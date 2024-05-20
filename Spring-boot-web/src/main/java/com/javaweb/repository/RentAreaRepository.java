package com.javaweb.repository;

import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {

    void deleteByBuildingIdIn(List<Long> ids);

    void deleteByBuildingIdIs(Long id);

}
