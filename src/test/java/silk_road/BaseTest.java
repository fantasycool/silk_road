package silk_road;

import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.UUID;

/**
 * Created by frio on 16/6/24.
 */
public class BaseTest {

    @Test
    public void test(){
        long a = System.currentTimeMillis();
        UUID.randomUUID();
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }
}
