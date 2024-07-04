package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.CustomerStatus;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private MessageUtils messageUtil;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/admin/customer-list")
    public ModelAndView getAllCustomer(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("staffmaps", iUserService.getStaffs());
        List<CustomerResponseDTO> customerList = new ArrayList<>(); // tim kiem khach hang
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            model.setStaffId(staffId);
            model.setIsActive(1L);
            customerList = iCustomerService.getAllCustomer(model);
        }
        else{
            model.setIsActive(1L);
            customerList = iCustomerService.getAllCustomer(model);
        }
        model.setListResult(customerList);
        model.setTotalItems(iCustomerService.countTotalCustomer(model));
        initMessageResponse(mav, request);
        DisplayTagUtils.of(request, model);
        mav.addObject("model", model);
        mav.addObject("customerList", customerList);
        mav.addObject("customerStatus", CustomerStatus.customerStatusList());
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
    @GetMapping(value="/admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerStatus", CustomerStatus.customerStatusList());
        return mav;
    }
    @GetMapping(value="/admin/customer-edit-{id}")
    public ModelAndView updateCustomer(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerStatus", CustomerStatus.customerStatusList());
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(id);
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        mav.addObject("customerEdit", customerDTO);
        mav.addObject("transactionType", TransactionType.transactionType());
        List<TransactionEntity> CSKHList = transactionRepository.findByCodeAndCustomer("CSKH", customerEntity);
        mav.addObject("CSKHList", CSKHList);
        List<TransactionEntity> DDXList = transactionRepository.findByCodeAndCustomer("DDX", customerEntity);
        mav.addObject("DDXList", DDXList);
        return mav;
    }

}
