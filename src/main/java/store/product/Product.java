package store.product;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final int quantity;

    public Product(final String name, final BigDecimal price, final int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(final ProductInfo productInfo) {
        this.name = productInfo.name();
        this.price = productInfo.price();
        this.quantity = productInfo.count();
    }

    public Product minus(final int quantity) {
        return new Product(this.name, this.price, this.quantity - quantity);
    }

    public ProductSnapshot getSnapshot() {
        return new ProductSnapshot(this.name, this.price, null);
    }

    public String getName() {
        return name;
    }

    public int getQuantity(final int quantity) {
        return Math.min(quantity, this.quantity);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
