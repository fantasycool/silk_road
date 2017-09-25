package test.service.meta;

import com.alibaba.fastjson.JSON;
import com.rpc.silkroad.service.meta.ServiceMeta;
import org.junit.Test;

/**
 * Created by frio on 2017/9/25.
 */
public class ServiceBeanMetaTest {

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

    interface TestService {
        void test(TestBean<TestBean1<String>> testBean);
    }

    @Test
    public void testServiceBeanMetaConstruct(){
        ServiceMeta serviceMeta = new ServiceMeta(TestService.class);
        System.out.println(JSON.toJSONString(serviceMeta));
    }
}
