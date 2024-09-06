package codesnippet.spring.rocketmq.service;

import codesnippet.spring.rocketmq.model.CartItemEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublishService {

    private final RocketMQTemplate broker;

    public PublishService(RocketMQTemplate broker) {

        this.broker = broker;
    }
    public void sendMessage() {

        this.broker.convertAndSend("cart-item-add-topic", new CartItemEvent("bike", 1));
        this.broker.convertAndSend("cart-item-add-topic", new CartItemEvent("computer", 2));
        this.broker.convertAndSend("cart-item-removed-topic", new CartItemEvent("bike", 1));
    }
}
