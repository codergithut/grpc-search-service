package grpc.search.oauth.server.s.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.util.concurrent.RateLimiter;
import com.zaxxer.hikari.HikariDataSource;
import grpc.search.oauth.server.s.grpc.client.DiscoveryClient;
import grpc.search.oauth.server.s.grpc.server.HelloWorldServer;
import grpc.search.oauth.server.s.grpc.server.MyServerInterceptor;
import grpc.search.oauth.server.s.server.*;
import grpc.search.oauth.server.s.server.impl.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by tianjian on 2019/6/26.
 */
@EnableAsync
@Configuration
@EnableScheduling
@PropertySource(value = { "classpath:config.properties" }, ignoreResourceNotFound = true)
public class BeanConfig {

    /**
     * 线程池
     * @return
     */
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }



    @Bean
    public MyServerInterceptor getMyServerInterceptor() {
        return new MyServerInterceptor();
    }

    @Value("${server.discoveryPort}")
    private int discoveryPort;

    @Value("${server.discoveryUrl}")
    private String discoveryUrl;

    @Bean
    public DiscoveryClient getDiscoverClient() {
        return new DiscoveryClient(discoveryUrl, discoveryPort);
    }

//    @Bean
//    public TaskServer getTaskServer() {
//        return new TaskServer();
//    }

    @Bean
    public ServiceLogServer getServiceLogServer() {
        return new ServiceLogServerImpl();
    }

    @Bean
    public SystemLogServer getSystemLogServer() {
        return new SystemLogServerImpl();
    }

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
    public DataSource getDruidDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driveName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        return dataSource;
    }

    /**
     * 数据服务代理，主要做限流
     * @return
     */
    @Bean("getDataBySqlServerProxy")
    public GetDataBySqlServerProxy getDataBySqlServerProxy(){
        return new GetDataBySqlServerProxy();
    }

    /**
     * 数据服务
     * @return
     */
    @Bean("getDataBySqlServer")
    public GetDataBySqlServer getDataBySqlServer() {
        GetDataBySqlServer getDataBySqlServer = new GetDataBySqlServer();
        return getDataBySqlServer;
    }

    /**
     * mysql查询引擎
     * @return
     */
    @Bean
    public DataSearchServer getMysqlDataSearchServer() {
        return new MysqlDataSearchServer();
    }


    @Value("${guava.RateLimiter.permitsPerSecond}")
    private double permitsPerSecond;
    /**
     * guava 限流器
     * @return
     */
    @Bean
    public RateLimiter getRateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond);
        return rateLimiter;
    }

    /**
     * 查询引擎注册器
     * @return
     */
    @Bean
    public DataSearchServerManage getDataSearchServerManage() {
        DataSearchServerManage dataSearchServerManage = new DataSearchServerManage();
        dataSearchServerManage.registerDataSearchServer("mysql", getMysqlDataSearchServer());
        return dataSearchServerManage;
    }

    /**
     * jwt验证密钥
     * @return
     */
    @Bean
    public Algorithm getAlgorithm() {
        Algorithm algorithmHS = Algorithm.HMAC256("secret");
        return algorithmHS;
    }

    /**
     * 缓存服务
     * @return
     */
    @Bean
    public CacheSearchDataServer getCacheSearchDataServer() {
        return new CacheSearchDataServerImpl();
    }

    /**
     * 安全验证服务，验证用户信息，验证字段权限信息
     * @return
     */
    @Bean
    public VerifySqlRequest getVerifySqlRequest() {
        return new VerifySqlRequestImpl();
    }

    /**
     * 服务器
     * @return
     */
    @Bean
    public HelloWorldServer getHelloWorldServer() {
        return new HelloWorldServer();
    }

}
