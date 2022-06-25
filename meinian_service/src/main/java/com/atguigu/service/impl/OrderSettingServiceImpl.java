package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> listData) {
        for (OrderSetting OrderSetting : listData) {
            int count = orderSettingDao.findOrderSettingByOrderDate(OrderSetting.getOrderDate());
            if (count>0){
                orderSettingDao.edit(OrderSetting);
            }else{
                orderSettingDao.add(OrderSetting);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String stratDate = date +"-1";
        String endDate = date +"-31";
        Map param = new HashMap();
        param.put("startDate",stratDate);
        param.put("endDate",endDate);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(param);

        List<Map> listData = new ArrayList<Map>();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (OrderSetting orderSetting : list) {
            Map map = new HashMap();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations", orderSetting.getReservations());
            listData.add(map);
        }
        return listData;
    }

    @Override
    public void editNumberByOrderDate(Map map) {

        try {
            String dateStr = (String) map.get("orderDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            int count = orderSettingDao.findOrderSettingByOrderDate(date);
            OrderSetting orderSetting = new OrderSetting();
            orderSetting.setNumber(Integer.parseInt((String)map.get("value")));
            orderSetting.setOrderDate(date);
            if (count>0){
                orderSettingDao.edit(orderSetting);
            }else{

                orderSettingDao.add(orderSetting);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
