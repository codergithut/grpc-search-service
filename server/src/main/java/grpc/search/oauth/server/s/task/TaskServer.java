package grpc.search.oauth.server.s.task;

import grpc.search.oauth.server.s.grpc.client.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by tianjian on 2019/6/28.
 */
@EnableScheduling
public class TaskServer {

    @Autowired
    private DiscoveryClient discoveryClient;


    @Scheduled(cron="0/30 * * * * ? ") //间隔5秒执行
    public void test() {
        discoveryClient.registerDiscoveryInfo();
    }
}
