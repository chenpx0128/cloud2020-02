package springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springcloud.entities.Payment;

@Mapper
public interface PaymentDao {

    public  int creat(Payment payment);

    public  Payment getpaymentId(@Param("id") Long id);
}
