package springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient  //springcloud  E版本后可以不加
@EnableCircuitBreaker//熔断器的开关
public class PaymentHystriMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystriMain8001.class, args);
    }


    /**要想使用hystrix-dashboard图形化监控此方法必须写
     * 此配置是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑，
     * ServletRegistrationBean是因为springboot的默认不是"/hystrix.stream",
     * 只要在自己的项目里配置上下面的servlet就可以了。
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
