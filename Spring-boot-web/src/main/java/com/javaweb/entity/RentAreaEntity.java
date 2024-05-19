package com.javaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentarea")
public class RentAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Getter
    @Setter
    private Long id;
    @Column(name = "value")
    @Getter
    @Setter
    private Long value;
    @Column(name = "createddate")
    @Getter
    @Setter
    private String createdDate;
    @Column(name = "modifieddate")
    @Getter
    @Setter
    private String modifiedDate;
    @Column(name = "createdby")
    @Getter
    @Setter
    private String createdBy;
    @Column(name = "modifiedby")
    @Getter
    @Setter
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    @Getter
    @Setter
    private BuildingEntity building;
}
