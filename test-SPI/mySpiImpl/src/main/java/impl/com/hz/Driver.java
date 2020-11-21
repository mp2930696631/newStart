package impl.com.hz;

/**
 * @author zehua
 * @date 2020/11/21 8:21
 */
public class Driver implements interf.com.hz.Driver {
    @Override
    public void getDriver() {
        System.out.println("get impl dirver success, test SPI success");
    }
}
