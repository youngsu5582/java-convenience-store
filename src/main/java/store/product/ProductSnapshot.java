package store.product;

import java.math.BigDecimal;

public record ProductSnapshot(String name, BigDecimal price, String promotionName) {
}
