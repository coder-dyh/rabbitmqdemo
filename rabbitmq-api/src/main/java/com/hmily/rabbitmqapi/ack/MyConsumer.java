package com.hmily.rabbitmqapi.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 自定义消费者
 */
@Slf4j
public class MyConsumer extends DefaultConsumer {

    private Channel channel;
    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag,  //消费者标签
                               Envelope envelope,
                               AMQP.BasicProperties properties,
                               byte[] body) throws IOException {
        log.info("------MyConsumer-----consume message----------");
        log.info("body: " + new String(body));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if((Integer)properties.getHeaders().get("num") == 0) {
                                                //是否为批量的，是否重回队列
            System.out.println("deliveryTag == 0 时的 index :" + envelope.getDeliveryTag());
            // channel.basicNack() 参数参考：https://www.cnblogs.com/piaolingzxh/p/5448927.html
            channel.basicNack(envelope.getDeliveryTag(), false, true);
        } else {
            System.out.println("delivery tag != 0  时的 index :" + envelope.getDeliveryTag());
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
