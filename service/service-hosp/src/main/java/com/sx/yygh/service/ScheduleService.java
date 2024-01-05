package com.sx.yygh.service;

import com.sx.yygh.model.hosp.Schedule;
import com.sx.yygh.vo.hosp.ScheduleOrderVo;
import com.sx.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    void save(Map<String, Object> paramMap);

    /**
     *  分页查询
     * @return
     */
    Page<Schedule> selectPage(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);

    //根据医院编号 和 科室编号 ，查询排班规则数据
    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

    /**
     * 获取排班可预约日期数据
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    Map<String, Object> getBookingScheduleRule(int page, int limit, String hoscode, String depcode);


    /**
     * 根据id获取排班
     * @param id
     * @return
     */
    Schedule getById(String id);


    /**
     * 修改排班
     */
    void update(Schedule schedule);


    ScheduleOrderVo getScheduleOrderVo(String scheduleId);
}
