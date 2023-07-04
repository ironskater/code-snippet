package codesnippet.spring.kafka.model;

import java.math.BigDecimal;
import java.util.Date;

public class DealDto {

    private BigDecimal price;
    private Date timestamp;

    public DealDto() {}

    public DealDto(BigDecimal price, Date timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
