package com.demain.springsecurity01helloword.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 登录页面(包含错误信息)
     *
     * @param error 错误信息
     * @param model 模型
     * @return 登录页面
     */
    @RequestMapping("/loginPageErrorInfo")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        return "loginPage";
    }
}
