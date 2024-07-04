package com.javaweb.repository.custom;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> getAllCustomer(CustomerSearchRequest customerSearchRequest);
    int countTotalCustomer(CustomerSearchRequest customerSearchRequest);
}
