package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.util.MD5Util;
import org.example.util.Result;
import org.example.util.ResultCodeEnum;
import org.example.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Administrator
 * description 针对表【news_user】的数据库操作Service实现
 * createDate 2024-10-21 17:42:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final TokenUtil tokenUtil;

    public UserServiceImpl(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    // 用户登入
    @Override
    public Result login(User user) {
        // 根据用户名和密码获取用户
        User loginUser = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getUserPwd, MD5Util.encrypt(user.getUserPwd()))
        );

        // 判断是否获取到用户
        if (loginUser != null) {
            // 用户名和密码正确
            return Result.ok(Map.of("token", tokenUtil.dumpToken(Map.of("uid", loginUser.getUid()))));
        } else {
            // 用户名或密码错误
            return new Result(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }

    // 根据 token 获取用户详细信息
    @Override
    public Result getUserInfo(String token) {
        // 1. 判断 token 是否有效
        Map<String, Object> tokenMap = tokenUtil.loadToken(token);
        if (token == null) {
            // 用户未登入
            return new Result(ResultCodeEnum.NOT_LOGIN);
        }

        // 2. 获取用户 uid
        Integer uid = (Integer) tokenMap.get("uid");

        // 3. 获取用户信息
        User user = getById(uid);
        if (user == null) {
            return Result.failure();
        }

        // 4. 密码清空
        user.setUserPwd("");

        // 5. 封装返回
        return Result.ok(Map.of("loginUser", user));
    }

    // 判断用户名是否被占用
    @Override
    public Result checkUserName(String username) {
        // 统计用户名存在的数量，如果为 0，则用户名可用
        if (count(new LambdaQueryWrapper<User>().eq(User::getUsername, username)) == 0) {
            return Result.ok();
        } else {
            return new Result(ResultCodeEnum.USERNAME_USED);
        }
    }

    @Override
    public Result regist(User user) {
        // 检查用户名是否可用
        if (count(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername())) > 0) {
            return new Result(ResultCodeEnum.USERNAME_USED);
        }

        // 对密码加密
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        // 注册
        if (save(user)) {
            return Result.ok();
        } else {
            return Result.failure();
        }
    }

    // 检查用户是否登入
    @Override
    public Result checkLogin(String token) {
        if (tokenUtil.loadToken(token) != null) {
            return Result.ok();
        } else {
            return new Result(ResultCodeEnum.NOT_LOGIN);
        }
    }
}
