package com.hy.spring.service;

import com.hy.mybatis_plus.mapper.TestMapper;
import com.hy.pojo.Test;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author pc
 * @Title: ImplTestService
 * @ProjectName Springmvc_spring_redis_mybits
 * @Description: 接口service实现类
 * @date 2018/10/12-11:52
 */
@Service("testService")
@CacheConfig(cacheNames = "manager")//缓存
public class ImplTestService implements ITestService{
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
     * @Description //根据id查询Test对象
     * @Date 11:56 2018/10/12
     * @Param [id]
     * @return com.hy.pojo.Test
     **/
    @Override
    @Cacheable(key="'test'")//增加数据到redis数据库并缓存 @CacheEvict(key="'test'")删除数据
    public Test queryById(Integer id) {
        return testMapper.selectById(id);
    }
}
