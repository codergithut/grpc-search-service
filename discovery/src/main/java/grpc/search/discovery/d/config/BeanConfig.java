package grpc.search.discovery.d.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.util.concurrent.RateLimiter;
import grpc.search.discovery.d.grpc.server.DiscoveryServer;
import grpc.search.discovery.d.server.DiscoveryInfo;
import grpc.search.discovery.d.server.GetDiscoverGrpcImpl;
import grpc.search.discovery.d.server.RegisterDiscoveryInfoGrpcImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by tianjian on 2019/6/26.
 */
@EnableAsync
@Configuration
@PropertySource(value = { "classpath:config.properties" }, ignoreResourceNotFound = true)
public class BeanConfig {

    @Bean
    public Log getLog() {
        return LogFactory.getLog("system");
    }

    @Value("${mysql.drivename}")
    private String driveName;

    @Value("${mysql.username}")
    private String userName;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.url}")
    private String url;


    @Bean
    public DruidDataSource getDruidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driveName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        return dataSource;
    }

    @Bean
    public DiscoveryServer getDiscoverServer() {
        return new DiscoveryServer();
    }

    @Bean
    public GetDiscoverGrpcImpl getDiscoverGrpc() {
        return new GetDiscoverGrpcImpl();
    }

    @Bean
    public RegisterDiscoveryInfoGrpcImpl getRegisterDiscoveryInfoGrpcImpl() {
        return new RegisterDiscoveryInfoGrpcImpl();
    }

    @Bean
    public DiscoveryInfo getDiscoveryInfo() {
        return new DiscoveryInfo();
    }

}
