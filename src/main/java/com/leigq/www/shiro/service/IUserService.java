package com.leigq.www.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leigq.www.shiro.domain.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
public interface IUserService extends IService<User> {

    /**
     * create by: leigq
     * description: 根据用户名获取用户
     * create time: 2019/6/28 16:19
     * @param userName 用户名
     * @return 用户
     */
    User findByUsername(String userName);

    /**
     * create by: leigq
     * description: 登录
     * create time: 2019/6/28 16:26
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    User login(String userName, String password);


    /**
     * create by: leigq
     * description: 登出
     * create time: 2019/6/28 16:30
     */
    void logout();

}
