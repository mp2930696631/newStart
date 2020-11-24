package starter.com.hz.configurationBean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zehua
 * @date 2020/11/24 20:25
 */
@ConfigurationProperties(prefix = "test.starter")
public class StarterConfiguration {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StarterConfiguration{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
