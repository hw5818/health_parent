package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:45
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    /**
     * 查询菜单id列表
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        // 调用服务查询菜单id列表
        List<Menu> list = menuService.findAll();
        return new Result(true, MessageConstant.QUERY_MENU_SUCCESS, list);
    }

}
