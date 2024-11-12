package store.product;

import store.order.OrderInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record PromotionProducts(Map<String, PromotionProduct> products) {

    public PromotionProducts() {
        this(new HashMap<>());
    }

    public PromotionProducts(final Map<String, PromotionProduct> products) {
        this.products = new HashMap<>(products);
    }

    public void add(final PromotionProduct product) {
        products.put(product.getName(), product);
    }

    public int getQuantity(final OrderInfo orderInfo) {
        return Optional.ofNullable(products.get(orderInfo.productName()))
                .map(product -> product.checkPromotionCount(orderInfo.count()))
                .orElse(0);
    }

    public PromotionProduct minus(final String productName, final int count) {
        return products.computeIfPresent(productName, (s, product) -> product.minus(count));
    }

    public int applyCount(final String productName, final int count) {
        return Math.min(products.get(productName)
                .applyPromotionCount(count), count);
    }
}
