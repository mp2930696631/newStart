package starter.com.hz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starter.com.hz.configurationBean.StarterConfiguration;

/**
 * @author zehua
 * @date 2020/11/24 21:04
 */
public class StarterService {

    @Autowired
    private StarterConfiguration configuration;

    public StarterConfiguration getConfiguration() {
        return configuration;
    }
}
