package hexagonalArchitecture.application.model;

import java.math.BigDecimal;
import java.util.Date;

public record History(Operation operation, Date date, BigDecimal amount, BigDecimal balance) {
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append(operation).append(", ").append(date.toString()).append(", ").append("amount: ")
                .append(amount.toString()).append(", ").append("new balance: ").append(balance.toString());
        return str.toString();
    }
}
