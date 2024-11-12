package store.product;

import store.order.OrderInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record Products(Map<String, Product> products) {

    public Products() {
        this(new HashMap<>());
    }

    public Products(final Map<String, Product> products) {
        this.products = new HashMap<>(products);
    }

    public void add(final Product product) {
        products.put(product.getName(), product);
    }

    public int getQuantity(final OrderInfo orderInfo) {
        return Optional.ofNullable(products.get(orderInfo.productName()))
                .map(product -> product.getQuantity(orderInfo.count()))
                .orElse(0);
    }

    public Product minus(final String productName, final int count) {
        return products.computeIfPresent(productName, (s, product) -> product.minus(count));
    }

    public boolean notContains(final String productName) {
        return !products.containsKey(productName);
    }
}
