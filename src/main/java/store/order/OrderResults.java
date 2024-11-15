package store.order;

import java.util.List;

public record OrderResults(List<OrderResult> orderResults) {
    public boolean isOrderSuccess() {
        return orderResults().stream()
                .allMatch(OrderResult::isSuccess);
    }
}
