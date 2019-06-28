package grpc.search.oauth.server.s.server;

/**
 * 记录用户的操作行为
 * Created by tianjian on 2019/6/26.
 */
public interface SystemLogServer {

    void warn(Object warn);

    void info(Object info);

    void error(Object error);


}
