package com.javaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="building")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "street")
    private String street;

    @Getter
    @Setter
    @Column(name = "ward")
    private String ward;

    @Getter
    @Setter
    @Column(name = "district")
    private String district;

    @Getter
    @Setter
    @Column(name = "structure")
    private String structure;

    @Getter
    @Setter
    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Getter
    @Setter
    @Column(name = "floorarea")
    private Long floorArea;

    @Getter
    @Setter
    @Column(name = "direction")
    private String direction;

    @Getter
    @Setter
    @Column(name = "level")
    private String level;

    @Getter
    @Setter
    @Column(name = "rentprice")
    private Long rentPrice;

    @Getter
    @Setter
    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Getter
    @Setter
    @Column(name = "servicefee")
    private String serviceFee;

    @Getter
    @Setter
    @Column(name = "carfee")
    private String carFee;

    @Getter
    @Setter
    @Column(name = "motofee")
    private String motofee;

    @Getter
    @Setter
    @Column(name = "overtimefee")
    private String overtimeFee;

    @Getter
    @Setter
    @Column(name = "waterfee")
    private String waterFee;

    @Getter
    @Setter
    @Column(name = "electricityfee")
    private String electricityFee;

    @Getter
    @Setter
    @Column(name = "deposit")
    private String deposit;

    @Getter
    @Setter
    @Column(name = "payment")
    private String payment;

    @Getter
    @Setter
    @Column(name = "renttime")
    private String rentTime;

    @Getter
    @Setter
    @Column(name = "decorationtime")
    private String decorationTime;

    @Getter
    @Setter
    @Column(name = "brokeragefee", nullable = true)
    private Double brokerageFee;

    @Getter
    @Setter
    @Column(name = "type")
    private String type;

    @Getter
    @Setter
    @Column(name = "note")
    private String note;

    @Getter
    @Setter
    @Column(name = "linkofbuilding")
    private String linkOfBuilding;

    @Getter
    @Setter
    @Column(name = "map")
    private String map;

    @Getter
    @Setter
    @Column(name = "avatar")
    private String avatar;

    @Getter
    @Setter
    @Column(name = "createddate")
    private String createdDate;

    @Getter
    @Setter
    @Column(name = "modifieddate")
    private String modifiedDate;

    @Getter
    @Setter
    @Column(name = "createdby")
    private String createdBy;

    @Getter
    @Setter
    @Column(name = "modifiedby")
    private String modifiedBy;

    @Getter
    @Setter
    @Column(name = "managername")
    private String managerName;

    @Getter
    @Setter
    @Column(name = "managerphone")
    private String managerPhone;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<RentAreaEntity> rentAreas = new ArrayList<>();
}



