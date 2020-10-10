package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;

import java.util.List;

public interface MenuService {
    //分页查询菜单信息
    PageResult<Menu> findPage(QueryPageBean queryPageBean);

    //新增菜单
    void add(Menu menu);

    void deleteById(int id);

    Menu findById(int id);

    void update(Menu menu);

    //动态菜单展示
    List<Menu> findMenu(String loginUsername);
}
