package interf.com.hz;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zehua
 * @date 2020/11/21 8:16
 */
public class DriverManager {
    public void getDriver() {
        final ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        final Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            final Driver driver = iterator.next();
            driver.getDriver();
        }
    }
}
