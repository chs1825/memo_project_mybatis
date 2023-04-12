package com.memo.controller;

import com.memo.service.TestService;
import com.memo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
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

        String path1 = "/Users/iirtech_009/devFiles/ksl/file/video/main/202304";
        String path2 = "/Users/chs/devFiles/ksl/file/video/main/202304";
        File file1 = new File(path1);
        File file2 = new File(path2);

        if(!file1.exists()){
            file1.mkdirs();
        }
        if(!file2.exists()){
            file2.mkdirs();
        }

        return "index";
    }

    @RequestMapping("/main")
    public String mainpage(Model model){

        List<UserVO> list = service.getAllUser();
        System.out.println("list = " + list);
        model.addAttribute("data",list);

        return "mainPage";
    }

    @PostMapping("/main/ajaxPost.do")
    @ResponseBody
    public String ajaxTest1(@RequestBody UserVO user){

        System.out.println("user = " + user);
        return "success";
    }

    @PostMapping("/main/ajaxNoAnotation.do")
    @ResponseBody
    public String ajaxTest2(UserVO param){
        System.out.println("param = " + param);
        return "oh!";
    }

    @PostMapping("/main/ajaxModel.do")
    public String ajaxTest3(UserVO param, Model model){
        System.out.println("param = " + param);

        model.addAttribute("test","test");
        return "jsonview";
    }


}
