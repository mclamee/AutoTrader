package com.wicky.trade;

public class PositionInfo {
    StockInfo stock;
    
    int balance;
    int avaliableBalance;
    double pnl;
    double pnlPercent;
    double costPrice;
    double marketPrice;
    double marketValue;
    
    @Override
    public int hashCode() {
        return stock.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PositionInfo){
            StockInfo stock2 = ((PositionInfo)obj).stock;
            return stock.equals(stock2);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "\nPositionInfo{" +
                "\nstock.code = " + stock.code +
                "\nstock.name = " + stock.name +
                "\nstock.marketName = " + stock.marketName +
                "\nstock.holderAccountNo = " + stock.holderAccountNo +
                "\nbalance = " + balance + 
                "\navaliableBalance = " + avaliableBalance +
                "\npnl = " + pnl +
                "\npnlPercent = " + pnlPercent +
                "\ncostPrice = " + costPrice +
                "\nmarketPrice = " + pnlPercent +
                "\nmarketPrice = " + marketPrice +
                "\nmarketValue = " + marketValue + 
                "\n}";
    }
    
}
