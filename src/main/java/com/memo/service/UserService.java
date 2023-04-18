package com.memo.service;

import com.memo.vo.UserVO;

import javax.servlet.http.HttpSession;

public interface UserService {

    public boolean loginAction(UserVO userVO, HttpSession session);

}
