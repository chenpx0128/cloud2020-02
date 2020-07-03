package springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * CLOUD-PAYMENT-SERVICE是服务端的名称
     * 使用openfeign时下面的方法必须和服务端一致
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/get/{id}")
     CommonResult<Payment> getpaymentId(@PathVariable("id") Long id);
}
