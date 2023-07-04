package codesnippet.spring.kafka.model;

import java.util.Date;

public class ContractDto {

    private Long contractId;
    private String symbol;
    private DealDto deal;
    private Date timestamp;

    public ContractDto() {}

    public ContractDto(Long contractId, String symbol, DealDto deal, Date timestamp) {
        this.contractId = contractId;
        this.symbol = symbol;
        this.deal = deal;
        this.timestamp = timestamp;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public DealDto getDeal() {
        return deal;
    }

    public void setDeal(DealDto deal) {
        this.deal = deal;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
