package com.wicky.trade;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Structure;

public class POINT extends Structure{
  public int x;
  public int y;
  @Override
  protected List<String> getFieldOrder() {
   // 元素的添加顺序必须与结构元素声明的顺序相同，添加的顺序一定不能写错（不管是基本变量还是结构体变量，或者是数组什么的，只需要添加名称就可以
   // 这个list返回的是封装结构体中的变量名称
   List<String> a = new ArrayList<String>();
   a.add("x");
   a.add("y");
   return a;
  }
}