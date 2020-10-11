package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:56
 */
public interface MenuDao {
    //分页查询菜单
    Page<Menu> findByCondition(String queryString);
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
