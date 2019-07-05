package com.leigq.www.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leigq.www.shiro.domain.entity.Role;
import com.leigq.www.shiro.domain.mapper.RoleMapper;
import com.leigq.www.shiro.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getUserRoles(Integer userId) {
        return roleMapper.getUserRoles(userId);
    }
}
