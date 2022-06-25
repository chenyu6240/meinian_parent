package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {
    @Autowired
    private TravelGroupDao travelGroupDao;


    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.add(travelGroup);
        Integer travelGroupId = travelGroup.getId();//拿不到id，必须进行主键回填
        setTravelGroupAndTravelItem(travelGroupId,travelItemIds);
    }
    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);
        Integer travelItemId = travelGroup.getId();
        travelGroupDao.delete(travelItemId);
        setTravelGroupAndTravelItem(travelItemId,travelItemIds);
    }

    @Override
    public List<TravelGroup> getAll() {
        return travelGroupDao.getAll();
    }

    @Override
    public PageResult getPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page page = travelGroupDao.getPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public TravelGroup getById(Integer id) {

        return travelGroupDao.getById(id);
    }

    @Override
    public List<Integer> getTravelItemIdsByTravelGroupId(Integer id) {
        return travelGroupDao.getTravelItemIdsByTravelGroupId(id);
    }



    private void setTravelGroupAndTravelItem(Integer travelGroupId, Integer[] travelItemIds) {
        if (travelItemIds!=null&&travelItemIds.length>0){
            for (Integer travelItemId : travelItemIds) {
                HashMap<String, Integer> paramData = new HashMap<>();
                paramData.put("travelGroupId",travelGroupId);
                paramData.put("travelItemId",travelItemId);
                travelGroupDao.addTravelGroupAndTravelItem(paramData);
            }
        }
    }
}
