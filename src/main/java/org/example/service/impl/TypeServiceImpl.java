package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.pojo.Type;
import org.example.service.TypeService;
import org.example.mapper.TypeMapper;
import org.example.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * description 针对表【news_type】的数据库操作Service实现
 * createDate 2024-10-22 16:40:50
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
        implements TypeService {

    // 查询所有类别
    @Override
    public Result findAllTypes() {
        return Result.ok(getBaseMapper().selectList(null));
    }
}




