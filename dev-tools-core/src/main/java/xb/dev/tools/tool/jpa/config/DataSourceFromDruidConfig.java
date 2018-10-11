package xb.dev.tools.tool.jpa.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Created by huangxb on 2018-08-15 11:54:18
 *
 *
 * Druid连接配置
 */
@Configuration
public class DataSourceFromDruidConfig {

    private Logger logger = LoggerFactory.getLogger(DataSourceFromDruidConfig.class);

    @Value("${spring.datasource.primary.url}")
    private String primary_dbUrl;
    @Value("${spring.datasource.primary.username}")
    private String primary_username;
    @Value("${spring.datasource.primary.password}")
    private String primary_password;
    @Value("${spring.datasource.driver-class-name}")
    private String primary_driverClassName;
    @Value("${spring.datasource.primary.initialSize}")
    private int primary_initialSize;
    @Value("${spring.datasource.primary.minIdle}")
    private int primary_minIdle;
    @Value("${spring.datasource.primary.maxActive}")
    private int primary_maxActive;
    @Value("${spring.datasource.primary.maxWait}")
    private int primary_maxWait;
    @Value("${spring.datasource.primary.removeAbandonedTimeout}")
    private int primary_removeAbandonedTimeout;
    @Value("${spring.datasource.primary.validationQuery}")
    private String primary_validationQuery;
    @Value("${spring.datasource.primary.testWhileIdle}")
    private boolean primary_testWhileIdle;
    @Value("${spring.datasource.primary.testOnBorrow}")
    private boolean primary_testOnBorrow;
    @Value("${spring.datasource.primary.testOnReturn}")
    private boolean primary_testOnReturn;
    @Value("${spring.datasource.primary.filters}")
    private String primary_filters;
//    @Value("${spring.datasource.primary.logSlowSql}")
//    private String primary_logSlowSql;

//
//    @Value("${spring.datasource.slave.url}")
//    private String slave_dbUrl;
//    @Value("${spring.datasource.slave.username}")
//    private String slave_username;
//    @Value("${spring.datasource.slave.password}")
//    private String slave_password;
//    @Value("${spring.datasource.driver-class-name}")
//    private String slave_driverClassName;
//    @Value("${spring.datasource.slave.initialSize}")
//    private int slave_initialSize;
//    @Value("${spring.datasource.slave.minIdle}")
//    private int slave_minIdle;
//    @Value("${spring.datasource.slave.maxActive}")
//    private int slave_maxActive;
//    @Value("${spring.datasource.slave.maxWait}")
//    private int slave_maxWait;
//    @Value("${spring.datasource.slave.removeAbandonedTimeout}")
//    private int slave_removeAbandonedTimeout;
//    @Value("${spring.datasource.slave.validationQuery}")
//    private String slave_validationQuery;
//    @Value("${spring.datasource.slave.testWhileIdle}")
//    private boolean slave_testWhileIdle;
//    @Value("${spring.datasource.slave.testOnBorrow}")
//    private boolean slave_testOnBorrow;
//    @Value("${spring.datasource.slave.testOnReturn}")
//    private boolean slave_testOnReturn;
//    @Value("${spring.datasource.slave.filters}")
//    private String slave_filters;
//    @Value("${spring.datasource.slave.logSlowSql}")
//    private String slave_logSlowSql;

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 添加初始化参数：initParams
        // 白名单
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// servletRegistrationBean.addInitParameter("deny", "192.168.1.73"); // 黑名单 deny>allow
		servletRegistrationBean.addInitParameter("loginUsername", primary_username);
		servletRegistrationBean.addInitParameter("loginPassword", primary_password);
		servletRegistrationBean.addInitParameter("logSlowSql", "true");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}

	@Bean
	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();
		wallFilter.setConfig(wallConfig());
		return wallFilter;
	}

	@Bean
	public WallConfig wallConfig() {
		WallConfig wallConfig = new WallConfig();
        //允许一次执行多条语句
		wallConfig.setMultiStatementAllow(true);
        //允许一次执行多条语句
		wallConfig.setNoneBaseStatementAllow(true);
		return wallConfig;
	}

    @Bean("primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    public DataSource druidPrimaryDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(primary_dbUrl);
        datasource.setUsername(primary_username);
        datasource.setPassword(primary_password);
        datasource.setDriverClassName(primary_driverClassName);
        datasource.setInitialSize(primary_initialSize);
        datasource.setMinIdle(primary_minIdle);
        datasource.setMaxActive(primary_maxActive);
        datasource.setMaxWait(primary_maxWait);
        datasource.setRemoveAbandonedTimeout(primary_removeAbandonedTimeout);
        datasource.setValidationQuery(primary_validationQuery);
        datasource.setTestWhileIdle(primary_testWhileIdle);
        datasource.setTestOnBorrow(primary_testOnBorrow);
        datasource.setTestOnReturn(primary_testOnReturn);
        try {
            datasource.setFilters(primary_filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

//    @Bean("slaveDataSource")
//    @Qualifier("slaveDataSource")
//    public DataSource druidSlaveDataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(slave_dbUrl);
//        datasource.setUsername(slave_username);
//        datasource.setPassword(slave_password);
//        datasource.setDriverClassName(slave_driverClassName);
//        datasource.setInitialSize(slave_initialSize);
//        datasource.setMinIdle(slave_minIdle);
//        datasource.setMaxActive(slave_maxActive);
//        datasource.setMaxWait(slave_maxWait);
//        datasource.setRemoveAbandonedTimeout(slave_removeAbandonedTimeout);
//        datasource.setValidationQuery(slave_validationQuery);
//        datasource.setTestWhileIdle(slave_testWhileIdle);
//        datasource.setTestOnBorrow(slave_testOnBorrow);
//        datasource.setTestOnReturn(slave_testOnReturn);
//        try {
//            datasource.setFilters(slave_filters);
//        } catch (SQLException e) {
//            logger.error("druid configuration initialization filter", e);
//        }
//        return datasource;
//    }
}
