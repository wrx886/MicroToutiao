package org.example.service;

import org.example.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.util.Result;

/**
* @author Administrator
* description 针对表【news_type】的数据库操作Service
* createDate 2024-10-22 16:40:50
*/
public interface TypeService extends IService<Type> {
    // 查询所有类别
    Result findAllTypes();
}
