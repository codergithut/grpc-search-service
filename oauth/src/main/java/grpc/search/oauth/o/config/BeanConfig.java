package grpc.search.oauth.o.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.auth0.jwt.algorithms.Algorithm;
import grpc.search.oauth.o.grpc.server.OauthServer;
import grpc.search.oauth.o.server.GetOauthTokenGrpcImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

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

    /**
     * jwt验证密钥
     * @return
     */
    @Bean
    public Algorithm getAlgorithm() {
        Algorithm algorithmHS = Algorithm.HMAC256("secret");
        return algorithmHS;
    }

    @Bean
    public OauthServer getOauthServer() {
        return new OauthServer();
    }

    @Bean
    public GetOauthTokenGrpcImpl getOauthTokenGrpc() {
        return new GetOauthTokenGrpcImpl();
    }


}
