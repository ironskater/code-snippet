package codesnippet.spring.rocketmq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartItemEvent {

    private String itemId;

    private int quantity;
}
