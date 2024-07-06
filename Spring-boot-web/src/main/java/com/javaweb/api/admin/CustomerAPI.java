package com.javaweb.api.admin;

import com.javaweb.model.dto.*;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping( "/api/customers")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;
    @PostMapping
    public String addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addOrUpdateCustomer(customerDTO);
        if(customerDTO.getId() == null ) return new String("Add customer successfully!");
        else return new String("Update customer successfully!");
    }
    @DeleteMapping
    public String deleteCustomer(@RequestBody List<Long> ids){
        //xuong DB xoa data
        customerService.deleteCustomer(ids);
        return new String("Delete customers successfully!");
    }
    @GetMapping("{id}/staffs")
    public ResponseDTO loadStaffs (@PathVariable("id") Long id){
        return customerService.listStaff(id);
    }

    @PutMapping
    public void assignCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        customerService.assignCustomer(assignmentCustomerDTO);
    }
    @PostMapping("/transaction")
    public void addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        customerService.addOrUpdateTransaction(transactionDTO);
    }
}
