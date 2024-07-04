package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.*;
import com.javaweb.enums.CustomerStatus;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerResponseDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<CustomerResponseDTO> getAllCustomer(CustomerSearchRequest customerSearchRequest) {
        List<CustomerEntity> customerEntities = customerRepository.getAllCustomer(customerSearchRequest);
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<CustomerResponseDTO>();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerResponseDTO customerResponseDTO = customerConverter.toCustomerResponseDTO(customerEntity);
            customerResponseDTOS.add(customerResponseDTO);
        }
        return customerResponseDTOS;
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        if(customerDTO.getId() == null && customerDTO.getStatus().isEmpty()){
            customerEntity.setStatus(CustomerStatus.CHUA_XU_LY.toString());
        }
        customerEntity.setIsActive(1L);
        customerRepository.save(customerEntity);
    }

    @Override
    public int countTotalCustomer(CustomerSearchRequest customerSearchRequest) {
        return  customerRepository.countTotalCustomer(customerSearchRequest);
    }

    @Override
    public void deleteCustomer(List<Long> ids) {
        List<CustomerEntity> customerEntities = customerRepository.findAllById(ids);
        for(CustomerEntity customerEntity : customerEntities){
            customerEntity.setIsActive(0L);
        }
        customerRepository.saveAll(customerEntities);
    }

    @Override
    public ResponseDTO listStaff(Long customerId) {
        // Lấy tất cả nhan viên (đã giao hoặc chưa giao)
        List<UserEntity> allUserEntities = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        //lấy tất cả nhân viên quản lý khach hang có id gửi veef
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(customerId);
        List<UserEntity> assignedUserEntities = new ArrayList<>();
        for(AssignmentCustomerEntity assignmentCustomerEntity : customerEntity.getAssignmentCustomerEntities()){
            assignedUserEntities.add(assignmentCustomerEntity.getStaff());
        }
        List<StaffResponseDTO> staffAssignment = new ArrayList<>();
        for(UserEntity userEntity : allUserEntities) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(userEntity.getId());
            staffResponseDTO.setFullName(userEntity.getFullName());
            if(assignedUserEntities.contains(userEntity)) {
                staffResponseDTO.setChecked("checked");
            }
            staffAssignment.add(staffResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffAssignment);
        responseDTO.setMessage("Success");
        return responseDTO;
    }

    @Override
    public void assignCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(assignmentCustomerDTO.getCustomerId());
        assignmentCustomerRepository.deleteAll(customerEntity.getAssignmentCustomerEntities());
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentCustomerDTO.getStaffs());
        for(UserEntity userEntity : staffs) {
            AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
            assignmentCustomerEntity.setStaff(userEntity);
            assignmentCustomerEntity.setCustomer(customerEntity);
            assignmentCustomerRepository.save(assignmentCustomerEntity);
        }
    }

    @Override
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        if (transactionDTO.getId() != null) {
            TransactionEntity transactionEntity = transactionRepository.findByIdIs(transactionDTO.getId());
            transactionEntity.setNote(transactionDTO.getTransactionDetail());
            transactionEntity.setModifiedBy(SecurityUtils.getPrincipal().getUsername());
            transactionEntity.setModifiedDate(new Date());
            transactionRepository.save(transactionEntity);
        } else {
            TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
            transactionEntity.setNote(transactionDTO.getTransactionDetail());
            transactionEntity.setCustomer(customerRepository.findCustomerEntityById(transactionDTO.getCustomerId()));
            transactionEntity.setCreatedDate(new Date());
            transactionEntity.setCreatedBy(SecurityUtils.getPrincipal().getUsername());
            UserEntity staff = userRepository.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), 1);
            transactionEntity.setStaff(staff);
            transactionRepository.save(transactionEntity);
        }
    }

}
