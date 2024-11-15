package store.order;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Orders(Map<String, OrderInfo> orderInfos) {
    private static final String SEPARATOR = ",";

    public static Orders from(final String line) {
        return new Orders(Arrays.stream(line.split(SEPARATOR))
                .map(OrderInfo::from)
                .collect(Collectors.toMap(OrderInfo::productName, Function.identity())));
    }

    public void fix(final OrderResult orderResult) {
        orderInfos.put(orderResult.name(), change(orderResult));
    }

    private OrderInfo change(final OrderResult orderResult) {
        final OrderInfo orderInfo = findByName(orderResult.name());
        return switch (orderResult.resultType()) {
            case ADDITION_PROMOTION -> orderInfo.plus(orderResult.quantity());
            case PROMOTION_STOCK_LACK -> orderInfo.minus(orderResult.quantity());
            case NONE -> orderInfo;
        };
    }

    private OrderInfo findByName(final String name) {
        return Optional.ofNullable(orderInfos.get(name))
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
