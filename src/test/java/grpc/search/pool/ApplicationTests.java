package grpc.search.pool;

import grpc.search.service.config.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by tianjian on 2019/6/26.
 */
public class ApplicationTests {

    public void test() throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(BeanConfig.class);
//        Task task = ctx.getBean(Task.class);
//        Future<Object> futureResult = task.run();
//        Object result = futureResult.get(5, TimeUnit.SECONDS);
    }
}
