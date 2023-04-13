package com.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/filePage")
public class FileController {

    @RequestMapping("/start.do")
    public String makePage(){
        return "filePage";
    }

    @PostMapping("/uploadFile.do")
    @ResponseBody
    public String fileUpload(@RequestParam MultipartFile file){
        
        String fileName = file.getOriginalFilename();
        System.out.println("fileName = " + fileName);
        
        String filePath = "a";
        
        
        
        return filePath;
    }
    

}
