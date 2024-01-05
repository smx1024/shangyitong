package com.sx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sx.yygh.model.user.UserInfo;
import com.sx.yygh.vo.user.LoginVo;
import com.sx.yygh.vo.user.UserAuthVo;
import com.sx.yygh.vo.user.UserInfoQueryVo;


import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> login(LoginVo loginVo);

    void userAuth(Long userId, UserAuthVo userAuthVo);

    //根据openid判断
    UserInfo selectWxInfoOpenId(String openid);

    //用户列表（条件查询带分页）
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    void lock(Long userId, Integer status);

    void approval(Long userId, Integer authStatus);

    Map<String, Object> show(Long userId);

}
