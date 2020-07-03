package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloud.lb.LoadBalancer;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";



    //自己定义的负载均衡类
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;


    /**
     * 调用自定义的轮询算法
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public  String getpaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
            if (instances == null || instances.size() <=  0){
                return null;
            }
        ServiceInstance instance = loadBalancer.instance(instances);

        URI uri = instance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }




    @GetMapping("/cosumber/payment/create")
    public CommonResult<Payment> create(Payment payment){
       log.info("客户端开始");
        return  restTemplate.postForObject(PAYMENT_URL+"/payment/creat",payment, CommonResult.class);
    }

    @GetMapping("/cosumber/payment/get/{id}")
    public CommonResult<Payment> getpayment(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }


    /**
     * restTemplate相关
     * @param id
     * @return
     */
    @GetMapping("/cosumber/payment/getForEntity/{id}")
    public CommonResult<Payment> getpayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }
}
