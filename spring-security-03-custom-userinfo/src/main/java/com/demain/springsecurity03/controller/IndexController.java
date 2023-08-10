package com.demain.springsecurity03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 *
 * @author demain_lee
 * @since 2023/8/8
 */
@Controller
public class IndexController {

    /**
     * 登录成功页面
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
