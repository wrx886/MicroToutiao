package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.example.pojo.Headline;
import org.example.pojo.vo.PortalVo;

import java.util.Map;

/**
 * @author Administrator
 * description 针对表【news_headline】的数据库操作Mapper
 * createDate 2024-10-21 17:42:39
 * Entity org.example.pojo.Headline
 */
public interface HeadlineMapper extends BaseMapper<Headline> {
    @MapKey("hid")
    IPage<Map<String, Object>> selectPageMap(Page<Map<String, Object>> page, PortalVo portalVo);

    @MapKey("hid")
    Map<Integer, Map<String, Object>> selectDetailByHid(int hid);
}




