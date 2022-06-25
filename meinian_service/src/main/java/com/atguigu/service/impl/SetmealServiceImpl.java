package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.RedisConstant;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    JedisPool jedisPool;


    @Override
        public void add(Integer[] travelgroupIds, Setmeal setmeal) {
            setmealDao.add(setmeal);
            Integer setmealId = setmeal.getId();
            setSetmealAndTravelGroup(travelgroupIds,setmealId);

            String pic = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List getSetmeal() {
        return setmealDao.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public Setmeal getSetmealById(Integer id) {
        return setmealDao.getSetmealById(id);
    }

    @Override
    public List<Map> getSetmealReport() {
        return setmealDao.getSetmealReport();
    }


    private void setSetmealAndTravelGroup(Integer[] travelgroupIds, Integer setmealId) {
        if (travelgroupIds!=null && travelgroupIds.length>0){
            for (Integer travelgroupId : travelgroupIds) {
                HashMap<String,Integer> paramData = new HashMap<>();
                paramData.put("setmeal_id",setmealId);
                paramData.put("travelgroup_id",travelgroupId);
                setmealDao.addSetmealAndTravelGroup(paramData);
            }
        }


    }


}
