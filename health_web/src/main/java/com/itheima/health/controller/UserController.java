package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/getUsername")
    public Result getUsername(){
        // SecurityContextHolder security容器的持有者
        // getContext 获取它的容器
        System.out.println("====Authentication==========" + SecurityContextHolder.getContext().getAuthentication().getName());
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("=======UserDetails=====" + user.getUsername());
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
    }

    @RequestMapping("/loginSuccess")
    public Result loginSuccess(){
        return new Result(true, MessageConstant.LOGIN_SUCCESS);
    }

    @RequestMapping("/loginFail")
    public Result loginFail(){
        return new Result(false, "用户名或密码不正确");
    }

    /**
     * 分页条件查询用户
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<User> pageResult = userService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_USER_SUCCESS,pageResult);
    }

    /**
     * 添加用户
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user, Integer[] roleIds){
        // 加密密码
        user.setPassword(encoder.encode(user.getPassword()));
        // 调用服务添加套餐
        Integer userId = userService.add(user, roleIds);
        return new Result(true, MessageConstant.ADD_USER_SUCCESS);
    }


    /**
     * 通过id查询用户信息
     */
    @GetMapping("/findById")
    public Result findById(int id){
        User user = userService.findById(id);
        return new Result(true, MessageConstant.QUERY_USER_SUCCESS,user);
    }

    /**
     * 查询选中的角色id集合
     */
    @GetMapping("/findRoleIdsByUserId")
    public Result findRoleIdsByUserId(int id){
        List<Integer> list = userService.findRoleIdsByUserId(id);
        return new Result(true, MessageConstant.QUERY_USER_SUCCESS,list);
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user, Integer[] roleIds){
        // 加密密码
        user.setPassword(encoder.encode(user.getPassword()));
        // 调用服务更新用户
        userService.update(user,roleIds);
        return new Result(true, MessageConstant.EDIT_USER_SUCCESS);
    }

    /**
     * 通过id删除用户
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        // 调用服务删除用户
        userService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_USER_SUCCESS);
    }
}
