package com.sx.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sx.yygh.model.user.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
