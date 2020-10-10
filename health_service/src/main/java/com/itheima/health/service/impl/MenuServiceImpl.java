package com.itheima.health.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: health_parent
 * @description:
 * @author: hw
 * @create: 2020-10-08 20:16
 **/
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    //分页查询菜单信息
    @Override
    public PageResult<Menu> findPage(QueryPageBean queryPageBean) {
        //Mapper接口方式的调用
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //模糊查询、判断是否有查询条件
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Menu> page = menuDao.findByCondition(queryPageBean.getQueryString());
        PageResult<Menu> pageResult = new PageResult<Menu>(page.getTotal(), page.getResult());
        return pageResult;
    }

    //新增菜单
    @Override
    public void add(Menu menu) {
        menuDao.add(menu);
    }

    //删除菜单
    @Override
    public void deleteById(int id) {
        menuDao.deleteById(id);
    }

    @Override
    public Menu findById(int id) {
        return menuDao.findById(id);
    }

    @Override
    public void update(Menu menu) {

        menuDao.update(menu);
    }


    //动态菜单展示
    @Override
    public List<Menu> findMenu(String loginUsername) {
        //父菜单
        List<Menu> parentMenu = new ArrayList<>();
        //子菜单
        List<Menu> childrenMenu = new ArrayList<>();
        //数据库获取的菜单集合
        List<Menu> menuList = menuDao.findMenu(loginUsername);
        for (Menu menu : menuList) {
            //parentMenuId等于null  就是父菜单
            if (menu.getParentMenuId() == null) {
                parentMenu.add(menu);
            } else {
                //否则就是子菜单
                childrenMenu.add(menu);
            }
        }

        //遍历父菜单、添加对应的子菜单
        for (Menu f : parentMenu) {
            //遍历子菜单、父菜单id == 子菜单里的父菜单id字段就添加子菜单到对应的父菜单
            for (Menu c : childrenMenu) {
                if (f.getId() == c.getParentMenuId()) {
                    f.getChildren().add(c);
                }
            }
        }
        return parentMenu;
    }

}
