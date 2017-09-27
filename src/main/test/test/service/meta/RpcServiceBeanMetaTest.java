package test.service.meta;

import com.alibaba.fastjson.JSON;
import com.rpc.silkroad.service.annotation.RpcService;
import com.rpc.silkroad.service.meta.ServiceMeta;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by frio on 2017/9/25.
 */
public class RpcServiceBeanMetaTest {

    static class TestBean<T> {
        T message;

        public T getMessage() {
            return message;
        }

        public void setMessage(T message) {
            this.message = message;
        }
    }

    static class TestBean1<B> {
        B bean1Message;
    }

    @RpcService("test.service.meta.TestService:1.0.0")
    interface TestService {
        Map<TestBean<String>, String> test(TestBean<TestBean1<String>> testBean);
    }

    @Test
    public void testServiceBeanMetaConstruct(){
        ServiceMeta serviceMeta = new ServiceMeta(TestService.class);
        System.out.println(JSON.toJSONString(serviceMeta));
    }
}
