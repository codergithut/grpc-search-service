package grpc.search.discovery.d.model;

/**
 * Created by tianjian on 2019/6/28.
 */
public class InstanceInfo {

    private String url;

    private int status;

    private Long refreshTime;

    private String severInfo;

    public String getSeverInfo() {
        return severInfo;
    }

    public void setSeverInfo(String severInfo) {
        this.severInfo = severInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
    }
}
