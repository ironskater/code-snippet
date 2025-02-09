package codesnippet.spring.rocketmq.service;

import codesnippet.spring.rocketmq.model.CartItemEvent;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
    topic = "cart-item-removed-topic",
    consumerGroup = "cart-consumer_cart-item-removed-topic"
)
@Slf4j
public class ConsumerItemRemoveService implements RocketMQListener<CartItemEvent> {

    @Override
    public void onMessage(CartItemEvent message) {

        log.info("Removing item: {}",
            JSON.toJSONString(message, JSONWriter.Feature.PrettyFormat));
    }
}
