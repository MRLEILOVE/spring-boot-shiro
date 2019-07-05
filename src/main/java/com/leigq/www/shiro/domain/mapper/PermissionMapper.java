package com.leigq.www.shiro.domain.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leigq.www.shiro.domain.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getRolePermissions(@Param("roleId") Integer roleId);
}
