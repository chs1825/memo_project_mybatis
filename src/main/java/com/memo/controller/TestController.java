package com.memo.controller;

import com.memo.service.TestService;
import com.memo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {

    private TestService service;

    public TestController(TestService service) {
        this.service = service;
    }


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/main")
    public String mainpage(Model model){

        List<UserVO> list = service.getAllUser();
        System.out.println("list = " + list);
        model.addAttribute("data",list);

        return "mainPage";
    }
}
