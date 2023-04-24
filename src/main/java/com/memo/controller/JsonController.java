package com.memo.controller;

import com.memo.convertJson.service.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/")
public class JsonController {

    private final JsonService jsonService;

    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @RequestMapping("/jsonPage.do")
    public String jsonPage(){
        return "excelToJSonPage";
    }


    @ResponseBody
    @RequestMapping("/convertExcel.do")
    public String convertJson(@RequestParam MultipartFile excelFile, HttpServletResponse res) throws IOException {

        log.debug("excelFile.getOriginalFilename():,{}", excelFile.getOriginalFilename());

        jsonService.convertJson(excelFile,res);


        log.debug("convertExcel.do 작동");
        return "hh";
    }



}
