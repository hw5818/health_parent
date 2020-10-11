package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 11:22
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    /**
     * 查询角色id列表
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        // 调用服务查询角色id列表
        List<Role> list = roleService.findAll();
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, list);
    }

    /**
     * 分页条件查询角色
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Role> pageResult = roleService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,pageResult);
    }

    /**
     * 添加角色
     */
    @PostMapping("/add")
    public Result add(@RequestBody Role role, Integer[] permissionIds, Integer[] menuIds){
        // 调用服务添加套餐
        Integer userId = roleService.add(role, permissionIds, menuIds);
        return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
    }


    /**
     * 通过id查询角色信息
     */
    @GetMapping("/findById")
    public Result findById(int id){
        Role role = roleService.findById(id);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, role);
    }

    /**
     * 查询选中的权限id集合
     */
    @GetMapping("/findPermissionIdsByRoleId")
    public Result findRoleIdsByUserId(int id){
        List<Integer> list = roleService.findPermissionIdsByRoleId(id);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,list);
    }

    /**
     * 查询选中的菜单id集合
     */
    @GetMapping("/findMenuIdsByRoleId")
    public Result findMenuIdsByRoleId(int id){
        List<Integer> list = roleService.findMenuIdsByRoleId(id);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,list);
    }

    /**
     * 更新角色
     */
    @PostMapping("/update")
    public Result update(@RequestBody Role role, Integer[] permissionIds, Integer[] menuIds){
        // 调用服务更新角色
        roleService.update(role,permissionIds, menuIds);
        return new Result(true, MessageConstant.EDIT_ROLE_SUCCESS);
    }

    /**
     * 通过id删除角色
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        // 调用服务删除角色
        roleService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_ROLE_SUCCESS);
    }

}
