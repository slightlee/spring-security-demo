package com.demain.springsecurity01helloword.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器
 *
 * @author demain_lee
 * @since 2023/8/7
 */
@Controller
public class LoginController {

    /**
     * 登录页面
     */
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    /**
     * 错误页面
     */
    @RequestMapping("/errorPage")
    public String errorPage() {
        return "errorPage";
    }
}
