package grpc.search.service.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.util.concurrent.RateLimiter;
import grpc.search.service.grpc.server.HelloWorldServer;
import grpc.search.service.server.*;
import grpc.search.service.server.impl.CacheSearchDataServerImpl;
import grpc.search.service.server.impl.MysqlDataSearchServer;
import grpc.search.service.server.impl.VerifySqlRequestImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by tianjian on 2019/6/26.
 */
@EnableAsync
@Configuration
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

    /**
     * guava 限流器
     * @return
     */
    @Bean
    public RateLimiter getRateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(0.2);
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
