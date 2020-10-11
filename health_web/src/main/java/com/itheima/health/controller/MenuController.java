package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.web.bind.annotation.*;
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


    //根据登录用户动态展示菜单
    @GetMapping("/findMenu")
    public Result findMenu(String loginUsername) {
        List<Menu> menuList = menuService.findMenu(loginUsername);
        return new Result(true, MessageConstant.GET_MENU_SUCCESS, menuList);
	}
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

    //分页查询菜单
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Menu> pageResult = menuService.findPage(queryPageBean);

        return new Result(true, "分页查询菜单成功", pageResult);
    }

    //新增菜单
    @PostMapping("/add")
    public Result add(@RequestBody Menu menu){
        menuService.add(menu);
        return new Result(true,"新增菜单成功");
    }

    //删除菜单信息
    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        menuService.deleteById(id);
        return new Result(true, "删除菜单成功");
    }


    @GetMapping("/findById")
    public Result findById(int id) {
        Menu menu = menuService.findById(id);
        return new Result(true,"获取菜单信息成功",menu);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu menu) {
        menuService.update(menu);
        return new Result(true,"编辑菜单成功");
    }
}
