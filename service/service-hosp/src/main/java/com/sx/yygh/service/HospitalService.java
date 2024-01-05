package com.sx.yygh.service;

import com.sx.yygh.model.hosp.Hospital;
import com.sx.yygh.vo.hosp.DepartmentVo;
import com.sx.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface HospitalService {

    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hascode);


    void updateStatus(String id, Integer status);

    Object show(String id);

    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    //根据医院编号，查询医院所有科室列表
    List<DepartmentVo> findDeptTree(String hoscode);

    /**
     * 根据医院编号获取医院名称接口
     * @param hoscode
     * @return
     */
    String getName(String hoscode);

    List<Hospital> findByHosnameLike(String hosname);


    Map<String, Object>  item(String hoscode);


}
