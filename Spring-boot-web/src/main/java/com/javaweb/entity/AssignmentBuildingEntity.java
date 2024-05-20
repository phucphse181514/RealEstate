package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity staffs;

	@ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildings;

}
