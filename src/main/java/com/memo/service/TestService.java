package com.memo.service;

import com.memo.mapper.TestMapper;
import com.memo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TestService {

    public List<UserVO> getAllUser();

}
