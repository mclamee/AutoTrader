package com.wicky.trade;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AutoTrader {
    public static void main(String[] args) {
        new AutoTrader().start();  
    }

    private Robot r;
    
    private void start() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        doTrade();
    }

    private void doTrade(){
        altTabSwitch();
        
        exractBasicInfo();
        
        int code = 601555;
        double price = 23.65;
        int amount = 500;
        
        buy(code, price, amount);
        sell(code, price, amount);
        
        altTabSwitch();
        
    }

    private BasicInfo exractBasicInfo() {
        pressF4();
        ctrlSave();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String importFilePath = "C:\\import-"+format.format(new Date()) + ".xls";
        input(importFilePath);
        altSave();
        r.delay(1000);
        
        BasicInfo info = new BasicInfo();
        
        File file = new File(importFilePath);
        if(file.exists()){
            BufferedReader reader = null;
            try {
                FileReader in = new FileReader(file);
                reader = new BufferedReader(in);
                String line = null;
                boolean title = true;
                while ((line = reader.readLine()) != null) {
                    line = line.replaceAll("\\s", "|");
                    List<String> l = Arrays.asList(line.split("\\|"));
                    System.out.println(l);
                    if(!title){
                        StockInfo stock = new StockInfo();
                        stock.code = l.get(0);
                        stock.name = l.get(1);
                        PositionInfo pos = new PositionInfo();
                        pos.stock = stock;
                        pos.balance = Integer.valueOf(l.get(2));
                        pos.avaliableBalance = Integer.valueOf(l.get(3));
                        pos.pnl = Double.valueOf(l.get(4));
                        pos.costPrice = Double.valueOf(l.get(5));
                        pos.pnlPercent = Double.valueOf(l.get(6));
                        pos.marketPrice = Double.valueOf(l.get(7));
                        pos.marketValue = Double.valueOf(l.get(8));
                        info.positions.add(pos);
                        stock.marketName = l.get(9);
                        stock.holderAccountNo = l.get(10);
                    }else{
                        title = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            file.delete();
        }
        System.out.println(info);
        
        return info;
    }

    private void buy(int code, double price, int amount) {
        pressF6();
        pressF1();
        input(code);
        pressEnter();
        pressEnter();
        input(price);
        pressEnter();
        input(amount);
        r.delay(500);
        pressB();
        r.delay(500);
        pressY();
        r.delay(500);
        pressSpace();
        r.delay(1000);
    }
    

    private void sell(int code, double price, int amount) {
        pressF6();
        pressF2();
        input(code);
        pressEnter();
        pressEnter();
        input(price);
        pressEnter();
        input(amount);
        r.delay(500);
        pressS();
        r.delay(500);
        pressY();
        r.delay(500);
        pressSpace();
        r.delay(1000);
    }
    
    private void altTabSwitch() {
        int vkAlt = KeyEvent.VK_ALT;
        int vkTab = KeyEvent.VK_TAB;
        combinePress(vkAlt, vkTab);
    }

    private void altSave() {
        int vkAlt = KeyEvent.VK_ALT;
        int vkS = KeyEvent.VK_S;
        combinePress(vkAlt, vkS);
    }
    
    private void ctrlSave() {
        int vkCtl = KeyEvent.VK_CONTROL;
        int vkS = KeyEvent.VK_S;
        combinePress(vkCtl, vkS);
    }
    
    private void pressColon() {
        int vkShift = KeyEvent.VK_SHIFT;
        int vkSC = KeyEvent.VK_SEMICOLON;
        combinePress(vkShift, vkSC);
    }
    
    private void combinePress(int vkAlt, int vkTab) {
        r.delay(1000);
        r.keyPress(vkAlt);
        r.keyPress(vkTab);
        r.delay(200);
        r.keyRelease(vkAlt);
        r.keyRelease(vkTab);
        r.delay(1000);
    }

    private void press(int keyCode) {
        r.keyPress(keyCode);
        r.keyRelease(keyCode);
        r.delay(10);
    }
    
    private void input(double price){
        String str = price + "";
        String price1 = str;
        String price2 = "00";
        if(str.contains(".")){
            price1 = str.split("\\.")[0];
            price2 = str.split("\\.")[1];
            if(price2.length() == 0){
                price2 = "00";
            }else if(price2.length() == 1){
                price2 += "0";
            }else if(price2.length() > 2){
                price2 = price2.substring(0, 2);
            }
        }
        input(price1);
        pressPeriod();
        input(price2);
    }

    private void input(int code){
        input(code + "");
    }

    private void input(String str) {
        if(str != null && str.length() > 0)
        for(char c : str.toCharArray()){
            switch (c) {
            case ':':
                pressColon();
                break;
            case '\\':
                press(KeyEvent.VK_BACK_SLASH);
                break;
            case '/':
                press(KeyEvent.VK_SLASH);
                break;
            case '-':
                press(KeyEvent.VK_MINUS);
                break;
            case '.':
                pressPeriod();
                break;
            default:
                try {
                    Field vkField = KeyEvent.class.getField(("VK_" + c).toUpperCase());
                    int key = (int)vkField.get(Class.forName("java.awt.event.KeyEvent"));
                    press(key);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    private void pressF1(){
        press(KeyEvent.VK_F1);
    }
    private void pressF2(){
        press(KeyEvent.VK_F2);
    }
    private void pressF3(){
        press(KeyEvent.VK_F3);
    }
    private void pressF4(){
        press(KeyEvent.VK_F4);
    }
    private void pressF5(){
        press(KeyEvent.VK_F5);
    }
    private void pressF6(){
        press(KeyEvent.VK_F6);
    }
    private void pressZ(){
        press(KeyEvent.VK_Z);
    }
    private void pressX(){
        press(KeyEvent.VK_X);
    }
    private void pressC(){
        press(KeyEvent.VK_C);
    }
    private void pressB(){
        press(KeyEvent.VK_B);
    }
    private void pressS(){
        press(KeyEvent.VK_S);
    }
    private void pressY(){
        press(KeyEvent.VK_Y);
    }
    private void pressN(){
        press(KeyEvent.VK_N);
    }
    private void pressEnter(){
        press(KeyEvent.VK_ENTER);
    }
    private void pressSpace(){
        press(KeyEvent.VK_SPACE);
    }
    private void pressPeriod() {
        press(KeyEvent.VK_PERIOD);
    }
}