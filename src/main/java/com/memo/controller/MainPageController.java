package com.memo.controller;

import com.memo.service.TestService;
import com.memo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class MainPageController {

    private TestService service;

    public MainPageController(TestService service) {
        this.service = service;
    }


    @RequestMapping("/ㅁ")
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

//    @RequestMapping("/mainPage.do")
    @RequestMapping("/")
    public String mainpage(Model model, HttpServletRequest request, String id){

        log.debug("id확인:,{}", id);
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        UserVO userVO = (UserVO) session.getAttribute("user"+id);
        log.debug("userVO 확인:,{}", userVO);
//        if(userVO == null){
//            return "redirect:/";
//        }else{
//            return "mainPage";
//        }
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

        model.addAttribute("data",param);
        return "jsonview";
    }


}
