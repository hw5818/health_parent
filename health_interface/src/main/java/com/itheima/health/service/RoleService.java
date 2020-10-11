package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Role;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 11:25
 */
public interface RoleService {
    /**
     * 查询角色id列表
     */
    List<Role> findAll();

    /**
     * 分页条件查询角色
     * @param queryPageBean
     * @return
     */
    PageResult<Role> findPage(QueryPageBean queryPageBean);

    /**
     * 添加角色
     * @param role
     * @param permissionIds
     * @param menuIds
     * @return
     */
    Integer add(Role role, Integer[] permissionIds, Integer[] menuIds);

    /**
     * 通过id查询角色信息
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 查询选中的权限id集合
     * @param id
     * @return
     */
    List<Integer> findPermissionIdsByRoleId(int id);

    /**
     * 查询选中的菜单id集合
     * @param id
     * @return
     */
    List<Integer> findMenuIdsByRoleId(int id);

    /**
     * 更新角色
     * @param role
     * @param permissionIds
     * @param menuIds
     */
    void update(Role role, Integer[] permissionIds, Integer[] menuIds);

    /**
     * 通过id删除角色
     * @param id
     */
    void deleteById(int id);
}
