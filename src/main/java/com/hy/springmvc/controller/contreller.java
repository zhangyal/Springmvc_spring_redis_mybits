package com.hy.springmvc.controller;

import com.hy.pojo.Test;
import com.hy.spring.service.ITestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author pc
 * @Title: contreller
 * @ProjectName Springmvc_spring_redis_mybits
 * @Description: springmvc_cintreller
 * @date 2018/10/12-11:57
 */
@RestController
@RequestMapping(value = "/test",produces = {MediaType.APPLICATION_JSON_VALUE})
public class contreller {
    @Resource(name = "testService")
    public ITestService testService;
    public ITestService getTestService() {
        return testService;
    }

    public void setTestService(ITestService testService) {
        this.testService = testService;
    }
    /**
     * @Author pc
     * @Description //根据id查询test
     * @Date 16:31 2018/10/12
     * @Param [id]
     * @return com.hy.pojo.Test
     **/
    @GetMapping("/test.action")
    @RequiresRoles(value = "add")
    public Test queryById(Integer id){
        return testService.queryById(id);
    }
    /**
     * @Author pc
     * @Description //测试shiro安全权限框架
     * @Date 16:32 2018/10/12
     * @Param [name]
     * @return java.lang.String
     **/
    @GetMapping("/login.action")
    public String login(String name,String pawd){
        UsernamePasswordToken token=new UsernamePasswordToken(name,pawd);
        Subject subject= SecurityUtils.getSubject();
        String key="认证成功";
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            key=e.getMessage();
        }catch (AuthenticationException e){
            key="密码错误";
        }
        return key;
    }
}
