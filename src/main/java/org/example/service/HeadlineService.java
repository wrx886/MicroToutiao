package org.example.service;

import org.example.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.vo.PortalVo;
import org.example.util.Result;

/**
 * @author Administrator
 * description 针对表【news_headline】的数据库操作Service
 * createDate 2024-10-21 17:42:39
 */
public interface HeadlineService extends IService<Headline> {
    // 分页查询
    Result findNewsPage(PortalVo portalVo);

    // 查询头条详细信息
    Result showHeadlineDetail(Integer hid);

    // 发布头条
    Result publish(Headline headline, String token);

    // 查询头条详细信息
    Result findHeadlineByHid(int hid, String token);

    // 修改头条
    Result updateHeadlineByHid(Headline headline, String token);

    // 删除头条
    Result removeByHid(int hid, String token);
}
