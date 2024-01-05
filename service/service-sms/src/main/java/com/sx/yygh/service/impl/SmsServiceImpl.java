package com.sx.yygh.service.impl;

import com.sx.yygh.component.SmsComponent;
import com.sx.yygh.service.SmsService;
import com.sx.yygh.vo.msm.MsmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    SmsComponent smsComponent;
    @Override
    public boolean send(MsmVo msmVo) {
        if(!StringUtils.isEmpty(msmVo.getPhone())) {
            String code = (String)msmVo.getParam().get("code");
            return smsComponent.sendCode(msmVo.getPhone(),code);
        }
        return false;
    }

}
