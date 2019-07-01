package com.leigq.www.shiro.test;

import com.leigq.www.shiro.base.BaseApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

public class ShiroApplicationTests extends BaseApplicationTests {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Test
    public void contextLoads() {
        // 测试时候可以正确获取 DataSourceProperties bean
        log.warn("DriverClassName is {}", dataSourceProperties.getDriverClassName());
    }

}
