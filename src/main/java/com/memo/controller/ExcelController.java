package com.memo.controller;

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

    private ExcelUtils excelUtils;

    public ExcelController(ExcelUtils excelUtils) {
        this.excelUtils = excelUtils;
    }

    @RequestMapping("/excelPage.do")
    public String excelPage(){
        return "excelPage";
    }


    @PostMapping("/uploadExcel.do")
    public String excelAction(@RequestParam MultipartFile excelFile, Model model) {

        log.debug("excelFile.getOriginalFilename():,{}", excelFile.getOriginalFilename());


        Map<Integer, List<String>> resMap = excelUtils.handleExcel(excelFile);
        log.debug("Map<Integer, List<String>> resMap = , {}" , resMap);

        model.addAttribute("mapData", resMap);
        model.addAttribute("listData", new String[]{"s", "d"});

        return "jsonview";
    }


}
