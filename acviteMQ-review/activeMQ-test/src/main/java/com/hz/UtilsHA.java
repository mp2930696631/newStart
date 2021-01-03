package com.hz;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;

/**
 * @author zehua
 * @date 2021/1/3 12:30
 */
public class UtilsHA {
    public static Connection getConn() {
        Connection connection = null;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "failover:(tcp://localhost:5671,tcp://localhost:5680)"
        );
        try {
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
