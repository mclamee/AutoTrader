package com.wicky.trade;

public class StockInfo {
    String code;
    String name;
    String marketName;
    String holderAccountNo;
    
    @Override
    public int hashCode() {
        return code.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StockInfo){
            String code2 = ((StockInfo)obj).code;
            return code.equals(code2);
        }
        return false;
    }
}
