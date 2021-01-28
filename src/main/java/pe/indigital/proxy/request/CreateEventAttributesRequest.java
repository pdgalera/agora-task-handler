package pe.indigital.proxy.request;

import java.math.BigDecimal;

public class CreateEventAttributesRequest {

    private BigDecimal amount;

    private String type;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
