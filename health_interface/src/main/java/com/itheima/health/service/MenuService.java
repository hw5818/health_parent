package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:52
 */
public interface MenuService {
    //分页查询菜单信息
    PageResult<Menu> findPage(QueryPageBean queryPageBean);
    /**
     * 查询菜单id列表
     * @return
     */
    List<Menu> findAll();

    //新增菜单
    void add(Menu menu);

    void deleteById(int id);

    Menu findById(int id);

    void update(Menu menu);

    //动态菜单展示
    List<Menu> findMenu(String loginUsername);
}
