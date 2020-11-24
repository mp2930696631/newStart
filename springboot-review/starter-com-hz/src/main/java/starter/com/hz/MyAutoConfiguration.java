package starter.com.hz;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import starter.com.hz.configurationBean.StarterConfiguration;

/**
 * @author zehua
 * @date 2020/11/24 20:07
 */
@Import(MyImportSelector.class)
@EnableConfigurationProperties(StarterConfiguration.class)
public class MyAutoConfiguration {
}
