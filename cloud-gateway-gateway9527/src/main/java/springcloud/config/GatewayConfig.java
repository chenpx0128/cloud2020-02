package springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 硬编码配置路由
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator custRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route",
                r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return  routes.build();
    }
}
