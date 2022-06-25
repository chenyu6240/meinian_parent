package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface TravelGroupDao {

    void add(TravelGroup travelGroup);
    void addTravelGroupAndTravelItem(HashMap<String, Integer> paramData);

    Page getPage(@Param("queryString") String queryString);

    TravelGroup getById(Integer id);

    List<Integer> getTravelItemIdsByTravelGroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void delete(Integer travelItemId);

    List<TravelGroup> getAll();

    List<TravelGroup> findTravelGroupById(Integer id);
}
