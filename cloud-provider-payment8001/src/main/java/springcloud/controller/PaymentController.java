package springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;


    /**
     *
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getpaymentLB(){
        return serverPort;
    }







    @PostMapping(value = "/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){

        int result = paymentService.creat(payment);
        System.out.println("******插入结果："+result);

        if(result >0){
            return  new CommonResult(200,"插入成功,serverPort"+serverPort,result);
        }else{
            return  new CommonResult(200,"插入失败",null);
        }
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getpaymentId(@PathVariable("id") Long id){

        Payment payment = paymentService.getpaymentId(id);
        System.out.println("******插入结果："+payment);

        if(payment != null){
            return  new CommonResult(200,"查询成功,serverPort"+serverPort,payment);
        }else{
            return  new CommonResult(200,"没有id为："+id+"的记录",null);
        }
    }

    /**
     * 获取服务端基本信息
     * @return
     */
    @GetMapping("/payment/discovery")
    public Object getdiscovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getInstanceId()+"\t"+instance.getHost()
                    +"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
