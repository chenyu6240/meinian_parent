package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void add(List<OrderSetting> listData);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByOrderDate(Map map);
}
