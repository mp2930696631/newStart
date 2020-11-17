import com.hz.aop.TestAop;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zehua
 * @date 2020/11/17 6:07
 */
public class TestSSM {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("spring.xml");

    @Test
    public void test01() {
        final TestAop testAop = applicationContext.getBean(TestAop.class);
        testAop.print();
    }

}
