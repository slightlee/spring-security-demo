package com.demain.springsecurity04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    /**
     * admin 接口
     */
    @RequestMapping("/api/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @RequestMapping("/api/admin/a")
    @ResponseBody
    public String adminA() {
        return "adminA";
    }

    @RequestMapping("/api/admin/a/b")
    @ResponseBody
    public String adminAB() {
        return "adminAB";
    }


    /**
     * user 接口
     */
    @RequestMapping("/api/user")
    @ResponseBody
    public String user() {
        return "user";
    }


}
