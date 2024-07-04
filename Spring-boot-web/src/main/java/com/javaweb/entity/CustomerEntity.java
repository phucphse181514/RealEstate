package com.javaweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "demand")
    private String demand;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Long isActive;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<AssignmentCustomerEntity> assignmentCustomerEntities = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<TransactionEntity> transactionEntities= new ArrayList<>();

}
