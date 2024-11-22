package org.example.controller;

import org.example.pojo.User;
import org.example.service.UserService;
import org.example.util.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 登入
    @PostMapping("login")
    public Result login(@RequestBody User user) {
        // 返回结果
        return userService.login(user);
    }

    // 根据 token 获取用户数据
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        // 返回结果
        return userService.getUserInfo(token);
    }

    // 判断用户名是否可用
    @PostMapping("checkUserName")
    public Result checkUserName(String username) {
        // 返回结果
        return userService.checkUserName(username);
    }

    // 用户注册
    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        // 返回结果
        return userService.regist(user);
    }

    // 检查是否登入
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {
        return userService.checkLogin(token);
    }
}
