package com.sx.yygh.order.receiver;

import com.sx.yygh.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderReceiver {

    @Autowired
    private OrderService orderService;
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = MqConst.QUEUE_TASK_8, durable = "true"),
//            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_TASK),
//            key = {MqConst.ROUTING_TASK_8}
//    ))
//    public void patientTips(Message message, Channel channel) throws IOException {
//        orderService.patientTips();
//    }

}
