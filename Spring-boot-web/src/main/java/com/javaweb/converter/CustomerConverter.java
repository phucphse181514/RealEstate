package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.CustomerStatus;
import com.javaweb.model.response.CustomerResponseDTO;
import com.javaweb.security.utils.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerResponseDTO toCustomerResponseDTO(CustomerEntity customerEntity) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customerEntity, CustomerResponseDTO.class);
        String status = CustomerStatus.customerStatusList().get(customerEntity.getStatus());
        customerResponseDTO.setStatus(status);
        return customerResponseDTO;
    }
}
