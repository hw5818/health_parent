package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private SetmealService setmealService;

    @GetMapping("/getSetmeal")
    public Result getSetmeal(HttpServletRequest request) {
        Jedis jedis = jedisPool.getResource();
        String setmealDetail = jedis.get("setmealList");
        if (setmealDetail == null) {
            // 查询所有的套餐
            List<Setmeal> setmealList = setmealService.findAll();
            // 拼接图片的完整路径
            setmealList.forEach(setmeal -> {
                setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
            });
            String strList = JSON.toJSONString(setmealList);
            jedis.set("setmealList", strList);
            jedis.close();
            // 再返回给页面
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealList);
        }
        JSONArray setmealList = JSON.parseArray(setmealDetail);
        jedis.close();
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealList);
    }

    /**
     * 查询套餐详情
     */
    @GetMapping("/findDetailById")
    public Result findDetailById(int id) {
        // 调用服务查询套餐详情
        Setmeal setmeal = setmealService.findDetailById(id);
        // 设置图片完整路径
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        // 返回
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    /**
     * 查询套餐详情
     */
    @GetMapping("/findDetailById2")
    public Result findDetailById2(int id) {
        // 调用服务查询套餐详情
        Setmeal setmeal = setmealService.findDetailById2(id);
        // 设置图片完整路径
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        // 返回
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    /**
     * 查询套餐详情
     */
    @GetMapping("/findDetailById3")
    public Result findDetailById3(int id) {
        Jedis jedis = jedisPool.getResource();
        String key = "setmeal" + "_" + id;
        String str = jedis.get(key);
        if (str == null) {
            // 调用服务查询套餐详情
            Setmeal setmeal = setmealService.findDetailById3(id);
            // 设置图片完整路径
            setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
            String strSetmeal = JSON.toJSONString(setmeal);
            //存入redis
            jedis.set(key, strSetmeal);
            jedis.close();
            //返回
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        }
        Setmeal setmeal = JSON.parseObject(str, Setmeal.class);
        jedis.close();
        // 返回
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    /**
     * 查询套餐信息
     */
    @GetMapping("/findById")
    public Result findById(int id) {
        // 调用服务查询套餐详情
        Setmeal setmeal = setmealService.findById(id);
        // 设置图片完整路径
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        // 返回
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }
}
