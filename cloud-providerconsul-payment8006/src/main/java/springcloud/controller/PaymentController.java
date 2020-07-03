package springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    protected String serverPort;


    @GetMapping(value = "/payment/consul")
    public String paymentconsul(){
        return "springcloud with consul:"+serverPort+"\t"+ UUID.randomUUID();
    }

}
