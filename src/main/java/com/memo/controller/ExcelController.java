package com.memo.controller;

import com.memo.service.ExcelService;
import com.memo.utils.Excel2JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/")
public class ExcelController {

    private ExcelService excelService;

    private Excel2JsonUtils excel2JsonUtils;

    public ExcelController(ExcelService excelService, Excel2JsonUtils excel2JsonUtils) {
        this.excel2JsonUtils = excel2JsonUtils;
        this.excelService = excelService;
    }

    @RequestMapping("/excelPage.do")
    public String excelPage(){
        return "excelPage";
    }


    @PostMapping("/uploadExcel.do")
    public String excelAction(@RequestParam MultipartFile excelFile, Model model) throws IOException {


        //1. poi 엑셀 처리
        log.debug("excelFile.getOriginalFilename():,{}", excelFile.getOriginalFilename());

        //2. 엑셀 데이터 변환 및 DB insert 처리
        excelService.insertExcel(excelFile);




        model.addAttribute("mapData", excel2JsonUtils.handleExcel(excelFile));

        return "jsonview";
    }


}
