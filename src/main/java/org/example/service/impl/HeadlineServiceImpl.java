package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.HeadlineMapper;
import org.example.pojo.Headline;
import org.example.pojo.vo.PortalVo;
import org.example.service.HeadlineService;
import org.example.util.Result;
import org.example.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * description 针对表【news_headline】的数据库操作Service实现
 * createDate 2024-10-21 17:42:39
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {

    private final HeadlineMapper headlineMapper;

    public HeadlineServiceImpl(HeadlineMapper headlineMapper, TokenUtil tokenUtil) {
        this.headlineMapper = headlineMapper;
        this.tokenUtil = tokenUtil;
    }

    private final TokenUtil tokenUtil;

    // 分页查询
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        // 分页查询
        Page<Map<String, Object>> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectPageMap(page, portalVo);

        // 结果封装
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());

        // 返回
        return Result.ok(Map.of("pageInfo", pageInfo));
    }

    // 查询头条详细信息
    @Override
    public Result showHeadlineDetail(Integer hid) {
        // 1. 查询对应的数据
        Map<String, Object> headlineDetail = headlineMapper.selectDetailByHid(hid).values().iterator().next();

        // 2. 更新
        Headline headline = new Headline();
        headline.setHid((Integer) headlineDetail.get("hid"));
        headline.setVersion((Integer) headlineDetail.get("version"));
        headline.setPageViews((Integer) headlineDetail.get("pageViews") + 1);
        updateById(headline);

        // 返回
        return Result.ok(Map.of("headline", headlineDetail));
    }

    // 发布头条
    @Override
    public Result publish(Headline headline, String token) {
        // 1. 补全用户 uid
        headline.setPublisher((Integer) tokenUtil.loadToken(token).get("uid"));
        // 2. 设置页面浏览量
        headline.setPageViews(0);
        // 3. 创建和修改时间
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        // 4. 插入数据并返回
        if (save(headline)) {
            return Result.ok();
        } else {
            return Result.failure();
        }
    }

    // 根据 hid 查询头条详细信息
    @Override
    public Result findHeadlineByHid(int hid, String token) {
        // 根据 hid 和 uid 获取头条
        Headline headline = getOne(new LambdaQueryWrapper<Headline>()
                .eq(Headline::getHid, hid)
                .eq(Headline::getPublisher, tokenUtil.loadToken(token).get("uid"))
        );

        // 判断头条是否为空并返回
        if (headline != null) {
            return Result.ok(Map.of("headline", headline));
        } else {
            return Result.failure();
        }
    }

    // 根据 hid 修改头条
    @Override
    public Result updateHeadlineByHid(Headline headline, String token) {
        // 1. 根据 hid 和 uid 获取原始头条
        Headline originalHeadline = getOne(new LambdaQueryWrapper<Headline>()
                .eq(Headline::getHid, headline.getHid())
                .eq(Headline::getPublisher, tokenUtil.loadToken(token).get("uid"))
        );

        // 失败返回
        if (originalHeadline == null) {
            return Result.failure();
        }

        // 2. 将新信息拷贝到原始信息中
        originalHeadline.setTitle(headline.getTitle());
        originalHeadline.setArticle(headline.getArticle());
        originalHeadline.setType(headline.getType());
        // 更新修改时间
        originalHeadline.setUpdateTime(new Date());

        // 3. 更新信息到数据库
        if (updateById(originalHeadline)) {
            return Result.ok();
        } else {
            return Result.failure();
        }
    }

    // 删除头条
    @Override
    public Result removeByHid(int hid, String token) {
        if (remove(new LambdaQueryWrapper<Headline>()
                .eq(Headline::getHid, hid)
                .eq(Headline::getPublisher, tokenUtil.loadToken(token).get("uid"))
        )) {
            // 删除成功
            return Result.ok();
        } else {
            // 删除失败
            return Result.failure();
        }
    }
}




