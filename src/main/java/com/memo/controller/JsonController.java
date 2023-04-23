package com.memo.controller;

import com.memo.service.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/")
public class JsonController {

    private JsonService jsonService;

    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @RequestMapping("/jsonPage.do")
    public String jsonPage(){
        return "excelToJSonPage";
    }


    @ResponseBody
    @RequestMapping("/convertExcel.do")
    public String convertJson(@RequestParam MultipartFile excelFile){

        log.debug("excelFile.getOriginalFilename():,{}", excelFile.getOriginalFilename());

        jsonService.convertJson(excelFile);


        log.debug("convertExcel.do 작동");
        return "hh";
    }



}
