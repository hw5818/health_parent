package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 11:27
 */
public interface RoleDao {
    /**
     * 查询角色id列表
     */
    List<Role> findAll();

    /**
     * 分页条件查询角色
     * @param queryString
     * @return
     */
    Page<Role> findByCondition(String queryString);

    /**
     * 添加角色
     * @param role
     */
    void add(Role role);

    /**
     * 添加角色与权限的关系
     * @param roleId
     * @param permissionId
     */
    void addRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 添加角色与菜单的关系
     * @param roleId
     * @param menuId
     */
    void addRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

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
     */
    void update(Role role);

    /**
     * 删除角色与权限的关系
     * @param id
     */
    void deleteRolePermission(Integer id);

    /**
     * 删除角色与菜单的关系
     * @param id
     */
    void deleteRoleMenu(Integer id);

    /**
     * 通过角色的id统计用户的个数
     * @param id
     * @return
     */
    int findCountByRoleId(int id);

    /**
     * 删除用户
     * @param id
     */
    void deleteById(int id);
}

