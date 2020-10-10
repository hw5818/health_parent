package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Permission;
import com.itheima.health.service.PermissionService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.*;

/**
 * @program: health_parent
 * @description:
 * @author: hw
 * @create: 2020-10-09 14:09
 **/
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    //权限信息分页显示
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Permission> pageResult = permissionService.findPage(queryPageBean);
        return new Result(true, "分页查询权限成功", pageResult);
    }

    //新增权限信息
    @PostMapping("/add")
    public Result add(@RequestBody Permission permission) {
        permissionService.add(permission);
        return new Result(true, "新增权限成功");
    }

    //删除权限信息
    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        permissionService.deleteById(id);
        return new Result(true, "删除权限信息成功");
    }

    //编辑权限
    @PostMapping("/update")
    public Result update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return new Result(true, "编辑权限成功");
    }

    //findById 根据id查询权限 编辑时回显信息
    @GetMapping("/findById")
    public Result findById(int id) {
        Permission permission = permissionService.findById(id);
        return new Result(true, "获取权限信息成功", permission);
    }
}
