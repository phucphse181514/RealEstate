package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="transaction")
public class TransactionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid")
    private UserEntity staff;

    @Column(name="createddate")
    private Date createdDate;

    @Column(name="modifieddate")
    private Date modifiedDate;

    @Column(name="createdby")
    private String createdBy;

    @Column(name="modifiedby")
    private String modifiedBy;

}
