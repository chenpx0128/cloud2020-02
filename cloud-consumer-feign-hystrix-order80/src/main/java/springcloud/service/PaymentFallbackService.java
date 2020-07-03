package springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentinfo_ok(Integer id) {

        return "PaymentFallbackService下面的paymentinfo_ok方法---调用异常";
    }

    @Override
    public String paymentinfo_timeout(Integer id) {

        return "PaymentFallbackService下面的paymentinfo_timeout方法---调用异常";
    }
}
