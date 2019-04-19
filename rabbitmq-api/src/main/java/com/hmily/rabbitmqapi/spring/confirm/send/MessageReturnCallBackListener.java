package com.hmily.rabbitmqapi.spring.confirm.send;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * 消息发送成功的监听回调类
 *
 * @link {https://www.jianshu.com/p/2c5eebfd0e95}
 */
@Component
public class MessageReturnCallBackListener implements RabbitTemplate.ConfirmCallback {

    /**
     * 消息发送成功的回调类
     *
     * @param correlationData 消息的唯一标识
     * @param b               ack 结果 true 表示消息发送成功 false 表示发送失败
     * @param s               失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("消息唯一标识：" + correlationData);
        System.out.println("确认结果：" + b);
        System.out.println("失败原因：" + s);
    }
}
