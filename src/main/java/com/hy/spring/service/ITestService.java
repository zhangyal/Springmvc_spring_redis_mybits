package com.hy.spring.service;

import com.hy.pojo.Test;

/**
 * @author pc
 * @Title: ITestService
 * @ProjectName Springmvc_spring_redis_mybits
 * @Description: service接口类
 * @date 2018/10/12-11:50
 */
public interface ITestService {
    public Test queryById(Integer id);
}
