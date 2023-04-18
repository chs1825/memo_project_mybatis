package com.memo.controller;

import com.memo.service.UserService;
import com.memo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
public class StartController {

    private UserService userService;

    public StartController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String loginPage(){
        return "loginPage";
    }

    @PostMapping("/loginAction.do")
    @ResponseBody
    public String loginAction(UserVO param, HttpSession session, HttpServletResponse response){

        log.debug("param : ,{}", param);
        boolean isAuth = userService.loginAction(param);


        if(isAuth){
            session.setAttribute("user"+param.getId(),param);
//            Cookie cookie = new Cookie("session_id", session.getId());
//            cookie.setMaxAge(60*60*1);
//            cookie.setPath("/");
//            response.addCookie(cookie);

            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }


}
