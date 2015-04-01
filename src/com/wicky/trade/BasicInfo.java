package com.wicky.trade;

import java.util.ArrayList;
import java.util.List;

public class BasicInfo {
   int totalBalance;
   int totalAvaliableBalance;
   double totalMarketValue;
   
   List<PositionInfo> positions = new ArrayList<>();
   
   @Override
    public String toString() {
        return "\nBasicInfo{\ntotalBalance = " + totalBalance + 
               "\ntotalAvaliableBalance = " + totalAvaliableBalance +
               "\ntotalMarketValue = " + totalMarketValue +
               "\npositions = " + positions.toString() + 
               "\n}";
    }
}
