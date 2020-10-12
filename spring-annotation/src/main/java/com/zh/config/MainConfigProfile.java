package com.zh.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zh.bean.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Profile:
 *      Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能
 *
 * 开发环境、测试环境、生产环境
 * 数据源 A、B、C
 *
 *
 * @Profile ，指定组件在那个环境下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *
 * 1. 加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
 * 2. 写在配置类上，只有是指定的环境的时候，整个配置里面的所有配置才能生效
 * 3. 没有标注环境标志的bean，在任何环境下都是加载的
 *
 * @author Zhaohui
 * @date 2020/10/12
 */
// @Profile("test")
@PropertySource("classpath:/dbconfig.yml")
@Configuration
public class MainConfigProfile implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver stringValueResolver;

    private String driverClass;

    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");

        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword("root");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword("root");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
        driverClass = stringValueResolver.resolveStringValue("${db.driverClass}");
    }
}
