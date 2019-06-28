package grpc.search.discovery.d.server;

import grpc.search.discovery.d.model.InstanceInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2019/6/28.
 */
public class DiscoveryInfo {

    private List<InstanceInfo> instanceInfos = new ArrayList<InstanceInfo>();

    /**
     * 获取当前可以使用对象
     * @return
     */
    public List<InstanceInfo> getInstanceInfo() {
        synchronized(instanceInfos) {
            if(!instanceInfos.isEmpty() && instanceInfos.size() > 0) {
                return instanceInfos.stream().filter(e -> e.getStatus() == 0).collect(Collectors.toList());
            }
        }
        return null;
    }

    /**
     * 清理当前超时信息
     */
    public void clearInstanceInfos() {
        List<InstanceInfo> newInstanceInfos = new ArrayList<InstanceInfo>();
        synchronized (instanceInfos) {
            Long time = new Date().getTime();
            newInstanceInfos = instanceInfos.stream().filter(e -> {
                if(e.getRefreshTime() < time - 10000){
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            instanceInfos = newInstanceInfos;
        }
    }

    /**
     * 保存注册主机信息
     * @param url
     */
    public void saveOrUpdateInstance(String url) {
        synchronized (instanceInfos) {
            Optional<InstanceInfo> optInstanceInfo = instanceInfos.stream().filter(e -> e.getUrl().equals(url)).findAny();
            if(optInstanceInfo.isPresent()) {
                optInstanceInfo.get().setRefreshTime(new Date().getTime());
                optInstanceInfo.get().setStatus(0);
            } else {
                InstanceInfo instanceInfo = new InstanceInfo();
                instanceInfo.setStatus(0);
                instanceInfo.setRefreshTime(new Date().getTime());
                instanceInfo.setUrl(url);
                instanceInfos.add(instanceInfo);
            }
        }
    }

    /**
     * 删除某个主机信息
     * @param url
     */
    public void removeInstance(String url) {
        synchronized (instanceInfos) {
            Optional<InstanceInfo> optInstanceInfo = instanceInfos.stream().filter(e -> e.getUrl().equals(url)).findAny();
            if(optInstanceInfo.isPresent()) {
                instanceInfos.remove(optInstanceInfo.get());
            }
        }
    }
}
