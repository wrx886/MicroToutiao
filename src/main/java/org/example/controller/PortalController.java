package org.example.controller;

import org.example.pojo.vo.PortalVo;
import org.example.service.HeadlineService;
import org.example.service.TypeService;
import org.example.util.Result;
import org.springframework.web.bind.annotation.*;

@RequestMapping("portal")
@RestController
@CrossOrigin
public class PortalController {
    private final TypeService typeService;

    private final HeadlineService headlineService;

    public PortalController(TypeService typeService, HeadlineService headlineService) {
        this.typeService = typeService;
        this.headlineService = headlineService;
    }

    // 查询所有类别
    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        return typeService.findAllTypes();
    }

    // 分页查询
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        return headlineService.findNewsPage(portalVo);
    }

    // 头条详情
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid) {
        return headlineService.showHeadlineDetail(hid);
    }
}
