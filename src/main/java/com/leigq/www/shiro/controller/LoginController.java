package com.leigq.www.shiro.controller;

import com.leigq.www.shiro.bean.Response;
import com.leigq.www.shiro.domain.entity.User;
import com.leigq.www.shiro.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：leigq
 * @date ：2019/6/28 16:55
 * @description：登录Controller
 */
@Slf4j
@RestController
public class LoginController {

    @Resource
    private IUserService iUserService;

    @Resource
    private Response response;

    /**
     * create by: leigq
     * description: 登录
     * create time: 2019/6/28 17:11
     *
     * @return 登录结果
     */
    @PostMapping("/login")
    public Response login(User user) {
        log.warn("进入登录.....");

        String userName = user.getUserName();
        String password = user.getPassword();

        if (StringUtils.isBlank(userName)) {
            return response.failure("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return response.failure("密码为空！");
        }

        User loginUser = iUserService.login(userName, password);
        // 登录成功返回用户信息
        return response.success("登录成功！", loginUser);
    }

    /**
     * create by: leigq
     * description: 登出
     * create time: 2019/6/28 17:37
     */
    @PostMapping("/logout")
    public Response logOut() {
        iUserService.logout();
        return response.success("登出成功！");
    }
}
