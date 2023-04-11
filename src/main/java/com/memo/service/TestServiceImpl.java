package com.memo.service;

import com.memo.mapper.TestMapper;
import com.memo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestMapper mapper;

    public List<UserVO> getAllUser() {

        return mapper.selectUserAll();
    }
}
