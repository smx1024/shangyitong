package com.sx.yygh.order.api;

import com.sx.yygh.user.client.SystemLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @SystemLog
    @RequestMapping("/test/order/api")
    public  void test() {
        System.out.println("test");
    }
}
