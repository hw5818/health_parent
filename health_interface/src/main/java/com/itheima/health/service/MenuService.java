package com.itheima.health.service;

import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:52
 */
public interface MenuService {
    /**
     * 查询菜单id列表
     * @return
     */
    List<Menu> findAll();

}
