package springcloud.service;


import org.apache.ibatis.annotations.Param;
import springcloud.entities.Payment;

public interface PaymentService{

    public  int creat(Payment payment);

    public  Payment getpaymentId(@Param("id") Long id);
}
