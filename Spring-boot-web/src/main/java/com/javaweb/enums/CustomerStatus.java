package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CustomerStatus {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý");

    private final String statusName;

    CustomerStatus(String statusName){
        this.statusName = statusName;
    }
    public String getStatusName(){
        return statusName;
    }
    public static Map<String, String> customerStatusList(){
        Map<String, String> statusList = new LinkedHashMap<>();
        for(CustomerStatus customerStatus : CustomerStatus.values()){
            statusList.put(customerStatus.toString(), customerStatus.getStatusName());
        }
        return statusList;
    }
}
