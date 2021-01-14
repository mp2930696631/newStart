package com.hz.consumer02.grayscale;

import com.hz.consumer02.entity.GrayscalePublishing;
import com.hz.consumer02.service.GrayscaleService;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用ribbon-discovery-filter-spring-cloud-starter
 *
 * @author zehua
 * @date 2021/1/14 15:35
 */
public class GrayscaleRule extends AbstractLoadBalancerRule {
    @Autowired
    private GrayscaleService grayscaleService;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    private Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        boolean flag = false;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            // 这些个server已经是经过了区域、serviceId筛选了的
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }
            // 上面是抄袭RandomRule

            final HttpRequest httpRequest = RestTemplateRequestHolder.get();
            // 这个对象是InterceptingClientHttpRequest的实例，可以获取头信息
            // 这里简单起见，就直接获取查询参数算了
            final URI uri = httpRequest.getURI();
            final String pathUserId = uri.getQuery().split("=")[1];
            String pathServiceId = uri.getHost();
            final GrayscalePublishing grayscale = grayscaleService.getGrayscaleByUserIdAndServiceId(pathUserId, pathServiceId);
            if (grayscale != null) {
                final Integer version = grayscale.getVersion();
                final List<Server> filterServers = filter(version.toString(), upList);
                server = getServer(filterServers);
                flag = true;
            }

            // 非灰度发布对象
            if (!flag) {
                server = getServer(upList);
            }

            // 下面是抄袭RandomRule
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;
    }

    private List<Server> filter(String version, List<Server> upServer) {
        List<Server> serverList = new ArrayList<>();
        if (upServer == null) {
            return serverList;
        }

        for (Server server : upServer) {
            DiscoveryEnabledServer discoveryEnabledServer = (DiscoveryEnabledServer) server;
            final Map<String, String> metadata = discoveryEnabledServer.getInstanceInfo().getMetadata();
            final String v = metadata.get("version");
            if (v != null && v.equals(version)) {
                serverList.add(server);
            }
        }

        return serverList;
    }

    private Server getServer(List<Server> servers) {
        int len = servers.size();
        int index = atomicInteger.getAndIncrement() % len;

        return servers.get(index);
    }
}
