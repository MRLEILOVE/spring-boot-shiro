package com.leigq.www.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leigq.www.shiro.domain.entity.Permission;
import com.leigq.www.shiro.domain.mapper.PermissionMapper;
import com.leigq.www.shiro.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> getRolePermissions(Integer roleId) {
        return baseMapper.getRolePermissions(roleId);
    }
}
