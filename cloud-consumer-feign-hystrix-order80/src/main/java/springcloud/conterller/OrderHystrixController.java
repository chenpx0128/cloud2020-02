package springcloud.conterller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.PaymentHystrixService;

import javax.annotation.Resource;

@RestController
//设置全局的降级方法，但是耦合性还是高，使用降级类较为合理请看本项目的service实现类
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentinfo_ok(id);
    }


    /**
     * 超过规定的时间后会自动访问降级的方法
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentinfo_timeout(@PathVariable("id") Integer id){
       // int age = 10/0;
        return paymentHystrixService.paymentinfo_timeout(id);
    }


    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，服务繁忙请10秒后再试";
    }

    //下面是全局的fallback
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试！！";
    }
}
