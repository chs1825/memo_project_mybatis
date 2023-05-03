package com.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SignHandelController {

    @RequestMapping("singHandPage.do")
    public String signPage(){

        return "signPage";
    }
}
