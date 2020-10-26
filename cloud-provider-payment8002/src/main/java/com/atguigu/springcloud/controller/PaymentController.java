package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author EiletXie
 * @Since 2020/3/9 12:06
 */
@RestController
@Slf4j
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
   public CommonResult create(@RequestBody Payment payment){

       int result = paymentService.create(payment);

       log.info("***插入结果"+result);
       if (result>0){
           return new CommonResult(200,"插入成功,serverPort:"+serverPort,result);
       }else {
           return new CommonResult(444,"插入失败",null);
       }
   }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){

        Payment result = paymentService.getPaymentById(id);

        log.info("***查询结果"+result);
        if (result != null){
            return new CommonResult(200,"查询成功,serverport:"+serverPort,result);
        }else {
            return new CommonResult(444,"没有数据",null);
        }
    }

}
