package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");
    private final String transactionName;
    TransactionType(String transactionName) {
        this.transactionName = transactionName;
    }
    public String getTransactionName(){
        return transactionName;
    }
    public static Map<String, String> transactionType(){
        Map<String, String> typeCodes = new LinkedHashMap<>();
        for(TransactionType type : TransactionType.values()){
            typeCodes.put(type.toString(), type.getTransactionName());
        }
        return typeCodes;
    }
}
