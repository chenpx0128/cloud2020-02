package springcloud.service;


import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import springcloud.entities.Payment;

public interface PaymentService{

    public  int creat(Payment payment);

    public  Payment getpaymentId(@Param("id") Long id);

}
