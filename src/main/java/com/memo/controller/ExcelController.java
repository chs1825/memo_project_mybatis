package com.memo.controller;

import com.memo.service.ExcelService;
import com.memo.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/")
public class ExcelController {

    private ExcelService excelService;
    private ExcelUtils excelUtils;

    public ExcelController(ExcelUtils excelUtils, ExcelService excelService) {
        this.excelUtils = excelUtils;
        this.excelService = excelService;
    }

    @RequestMapping("/excelPage.do")
    public String excelPage(){
        return "excelPage";
    }


    @PostMapping("/uploadExcel.do")
    public String excelAction(@RequestParam MultipartFile excelFile, Model model) {


        //1. poi 엑셀 처리
        log.debug("excelFile.getOriginalFilename():,{}", excelFile.getOriginalFilename());
        Map<Integer, List<String>> resMap = excelUtils.handleExcel(excelFile);
        log.debug("Map<Integer, List<String>> resMap = , {}" , resMap);

        //2. DB 처리




        model.addAttribute("mapData", resMap);

        return "jsonview";
    }


}
