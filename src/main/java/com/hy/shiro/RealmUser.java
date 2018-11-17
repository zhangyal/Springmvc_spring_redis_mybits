package com.hy.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.mybatis_plus.mapper.TestMapper;
import com.hy.pojo.Test;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pc
 * @Title: RealmUser
 * @ProjectName Springmvc_spring_redis_mybits
 * @Description: 登录管理器
 * @date 2018/10/15-10:40
 */
public class RealmUser extends AuthorizingRealm {
    @Resource(name = "testMapper")
    private TestMapper testMapper;
    public TestMapper getTestMapper() {
        return testMapper;
    }

    public void setTestMapper(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    /**
     * @Author pc
     * @Description //权限
     * @Date 14:55 2018/10/15
     * @Param [principals]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        System.out.println(principal);
        Set<String> set=new HashSet<>();
        set.add("add");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(set);
        return info;
    }
    /**
     * @Author pc
     * @Description //认证
     * @Date 14:57 2018/10/15
     * @Param [token]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
        //得到username
        String username=usernamePasswordToken.getUsername();
        //调用mapper方法查询username对应的用户
        Test test=testMapper.selectOne(new QueryWrapper<Test>().eq("name",username));
        //若用户不存在抛出UnknownAccountException异常
        if(test==null){
            throw new UnknownAccountException("当前用户名输入错误");
        }
        //密码加盐(密)(String)
        ByteSource salt = ByteSource.Util.bytes(String.valueOf(test.getId()));
        //SimpleAuthenticationInfo 参数数据库用户名，数据库密码，盐值，realm名字
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,test.getPawd(),salt,getName());
        return info;
    }
}
