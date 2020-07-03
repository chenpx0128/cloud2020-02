package springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {


    /****************************************服务降级*********************************************/
    /**
     *
     * @param id
     * @return
     */
    public String paymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_ok,id:"+id+"\t"+"哈哈哈";
    }


    /**
     * 超过规定的时间后会自动访问降级的方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "7000")
    })
    public String paymentTimeout(Integer id){
      //  int age = 10/0;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程："+Thread.currentThread().getName()+"id:"+id+"\t"+"耗时（秒）成功啦！！";
    }

    public String paymentTimeoutHandler(Integer id){
        return "线程："+Thread.currentThread().getName()+"8001系统报错请稍后再试,id:"+id+"\t"+"哭哭哭";
    }


    /****************************************服务熔断*********************************************/

    @HystrixCommand(fallbackMethod = "paymentCircuiBreaker_fallback",commandProperties = {
     //是否开启断路器
      @HystrixProperty(name = "circuitBreaker.enabled",value="true"),
     //请求次数
      @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),
     //时间窗口期
      @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000"),
      //失败率达到多少后跳闸
      @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="60")
    })
    public String paymentCircuiBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("*********id,不能为负数");
        }
        String serialnumber = IdUtil.simpleUUID();
        return "线程"+Thread.currentThread().getName()+"调用成功，流水号为："+serialnumber;
    }


    public String paymentCircuiBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id不能为负数，请稍后再试"+id;
    }
}
