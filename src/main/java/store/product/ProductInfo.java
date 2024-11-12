package store.product;

import java.math.BigDecimal;

public record ProductInfo(
        String name,
        BigDecimal price,
        int count,
        String promotionName
) {

    public boolean isPromotion() {
        return promotionName != null;
    }
}
