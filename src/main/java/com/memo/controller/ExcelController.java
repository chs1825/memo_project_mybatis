package com.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ExcelController {

    @RequestMapping("/excelPage.do")
    public String excelPage(){
        return "excelPage";
    }



}
