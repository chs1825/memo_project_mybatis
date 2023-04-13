package com.memo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/filePage")
@Slf4j
public class FileController {


    @RequestMapping("/start.do")
    public String makePage(){
        return "filePage";
    }

    @PostMapping("/uploadFile.do")
    @ResponseBody
    public String fileUpload(@RequestParam MultipartFile file){
        
        String fileName = file.getOriginalFilename();

        log.info("fileName : {} ," , fileName);
        System.out.println("fileName = " + fileName);
        
        String filePath = "a";


        
        
        
        return filePath;
    }
    

}
