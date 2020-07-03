package springcloud.service;

import org.springframework.stereotype.Service;
import springcloud.dao.PaymentDao;
import springcloud.entities.Payment;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl  implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int creat(Payment payment) {
        return paymentDao.creat(payment);
    }

    @Override
    public Payment getpaymentId(Long id) {
        return paymentDao.getpaymentId(id);
    }
}
