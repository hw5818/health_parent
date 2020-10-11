package com.itheima.health.dao;

import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:56
 */
public interface MenuDao {
    /**
     * 查询菜单id列表
     * @return
     */
    List<Menu> findAll();

}
