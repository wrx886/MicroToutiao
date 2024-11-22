package org.example.controller;

import org.example.pojo.Headline;
import org.example.service.HeadlineService;
import org.example.util.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
public class HeadlineController {
    private final HeadlineService headlineService;

    public HeadlineController(HeadlineService headlineService) {
        this.headlineService = headlineService;
    }

    // 发布头条
    @RequestMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {
        return headlineService.publish(headline, token);
    }

    // 根据 id 查询头条
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(int hid, @RequestHeader String token) {
        return headlineService.findHeadlineByHid(hid, token);
    }

    // 修改头条
    @PostMapping("update")
    public Result update(@RequestBody Headline headline, @RequestHeader String token) {
        return headlineService.updateHeadlineByHid(headline, token);
    }

    // 删除头条
    @PostMapping("removeByHid")
    public Result removeByHid(int hid, @RequestHeader String token) {
        return headlineService.removeByHid(hid, token);
    }
}
