package com.sx.yygh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sx.yygh.model.hosp.HospitalSet;
import com.sx.yygh.vo.order.SignInfoVo;


public interface HospitalSetService extends IService<HospitalSet> {
    String getSignKey(String hoscode);
    SignInfoVo getSignInfoVo(String hoscode);

}
