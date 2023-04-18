package com.memo.service;

import com.memo.mapper.UserMapper;
import com.memo.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

    private UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    public boolean loginAction(UserVO userVO, HttpSession session) {

        String id = userVO.getId();
        String pass = userVO.getPass();

        UserVO user = mapper.selectById(id);
        if(user != null && user.getPass().equals(pass)){

            session.setAttribute("loginUser",user);
            return true;
        }else{
            return false;
        }
    }
}
