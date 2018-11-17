package com.hy.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author pc
 * @Title: RealmDemo
 * @ProjectName Springmvc_spring_redis_mybits
 * @Description: 安全权限管理器类
 * @date 2018/10/12-16:08
 */
public class RealmDemo extends AuthorizingRealm {
    /**
     * @Author pc
     * @Description //权限
     * @Date 16:17 2018/10/12
     * @Param [principals]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限");
        return null;
    }
    /**
     * @Author pc
     * @Description //认证
     * @Date 16:17 2018/10/12
     * @Param [token]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        return null;
    }
}
