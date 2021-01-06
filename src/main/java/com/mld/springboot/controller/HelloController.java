package com.mld.springboot.controller;

import com.mld.springboot.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    /**
     * 接收List参数时 必须加上@RequestParam注解指定参数名
     */
    @ResponseBody
    @RequestMapping("/hello/{id}/say/{userName}")
    public Map<String, Object> hello(@PathVariable Integer id, @PathVariable String userName,
                                     @PathVariable Map<String, String> pv,
                                     String sex,
                                     @RequestParam("interests") List<String> interests,
                                     @CookieValue("Idea-34f1c5fa") String cookieValue,
                                     @CookieValue("Idea-34f1c5fa") Cookie cookie) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userName", userName);
        map.put("pv", pv);
        map.put("sex", sex);
        map.put("interests", interests);
        map.put("cookieValue", cookieValue);
        map.put("cookie-name", cookie.getName());
        map.put("cookie-value", cookie.getValue());
        return map;
    }

    @ResponseBody
    @RequestMapping("/testRequestBody")
    public UserModel testRequestBody(UserModel userModel, String userName) {
//        Map<String, Object> map = new HashMap<>(1);
//        map.put("content", content);
        System.out.println(userModel);
        System.out.println(userName);
        return userModel;
    }

    @RequestMapping("/testForward")
    public String testForward(HttpServletRequest request) {
        request.setAttribute("name","wangwu");
        request.setAttribute("msg","i am wangwu");
        return "redirect:/success";
    }

    @ResponseBody
    @RequestMapping("/success")
    public String success(@RequestAttribute("name") String name, HttpServletRequest request) {
        System.out.println(name);
        Object msg = request.getAttribute("msg");
        System.out.println(msg);
        return name + msg;
    }
}
