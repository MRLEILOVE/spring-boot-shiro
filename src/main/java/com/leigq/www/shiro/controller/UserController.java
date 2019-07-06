package com.leigq.www.shiro.controller;


import com.leigq.www.shiro.bean.Response;
import com.leigq.www.shiro.domain.entity.User;
import com.leigq.www.shiro.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private Response response;

    /**
     * 用户查询.
     * @return
     */
    @GetMapping("/userList")
    @RequiresPermissions("user:view")//权限管理;
    public Response listUsers(){
        List<User> users = iUserService.listUsers();
        return response.success("查询成功！", users);
    }

    /**
     * 用户添加;
     * @return
     */
    @PostMapping("/userAdd")
    @RequiresPermissions("user:add")//权限管理;
    public String userInfoAdd(){
        return "userAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @DeleteMapping("/userDel")
    @RequiresPermissions("user:del")//权限管理;
    public String userDel(){
        return "userDel";
    }

}
