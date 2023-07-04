package codesnippet.application_prototypes.model;

import java.math.BigDecimal;

public class Rsp {

    private BigDecimal actualTradePrice;

    public Rsp() {}

    public Rsp(BigDecimal actualTradePrice) {
        this.actualTradePrice = actualTradePrice;
    }

    public BigDecimal getActualTradePrice() {
        return actualTradePrice;
    }

    public void setActualTradePrice(BigDecimal actualTradePrice) {
        this.actualTradePrice = actualTradePrice;
    }
}
