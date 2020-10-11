package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:54
 */
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 查询菜单id列表
     * @return
     */
    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }
}
