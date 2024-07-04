package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerResponseDTO;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponseDTO> getAllCustomer(CustomerSearchRequest customerSearchRequest);
    void addOrUpdateCustomer(CustomerDTO customerDTO);
    int countTotalCustomer(CustomerSearchRequest customerSearchRequest);
    void deleteCustomer(List<Long> ids);
    ResponseDTO listStaff(Long customerId);
    void assignCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
}
