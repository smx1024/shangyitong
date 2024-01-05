package com.sx.yygh.service;

import com.sx.yygh.vo.msm.MsmVo;

public interface SmsService {
    boolean send(MsmVo msmVo);
}
