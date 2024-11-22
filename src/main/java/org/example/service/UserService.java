package org.example.service;

import org.example.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.util.Result;

/**
 * @author Administrator
 * description 针对表【news_user】的数据库操作Service
 * createDate 2024-10-21 17:42:39
 */
public interface UserService extends IService<User> {
    // 用户登入
    Result login(User user);

    // 根据 token 获取用户详细信息
    Result getUserInfo(String token);

    // 判断用户名是否被占用
    Result checkUserName(String username);

    // 用户注册
    Result regist(User user);

    // 检查用户是否登入
    Result checkLogin(String token);
}
