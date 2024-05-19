package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum typeCode {
    NOI_THAT("Nội Thất"),

    TANG_TRET("Tầng Trệt"),

    NGUYEN_CAN("Nguyên Căn");

    private final String typeCodeName;

    typeCode(String typeCodeName) {
        this.typeCodeName = typeCodeName;
    }
    public String getTypeCodeName() {
        return typeCodeName;
    }
    public static Map<String,String> getTypeCode(){
        Map<String,String> listTypeCode = new LinkedHashMap<String,String>();
        for(typeCode item : typeCode.values()){
            listTypeCode.put(item.toString(),item.getTypeCodeName());
        }
        return listTypeCode;
    }
}
