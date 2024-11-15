package store.fixture;

import store.product.Product;
import store.product.ProductSnapshot;
import store.product.PromotionProduct;

import java.math.BigDecimal;

public class ProductFixture {
    public static Product COCA_COLA(final int quantity) {
        return new Product("콜라", BigDecimal.valueOf(1000), quantity);
    }

    public static Product ORANGE_JUICE(final int quantity) {
        return new Product("오렌지주스", BigDecimal.valueOf(1800), quantity);
    }

    public static PromotionProduct COLA_TWO_PLUS_ONE(final int quantity) {
        return new PromotionProduct(COCA_COLA(quantity), PromotionFixture.TWO_PLUS_ONE());
    }

    public static PromotionProduct ORANGE_JUICE_MD(final int quantity) {
        return new PromotionProduct(ORANGE_JUICE(quantity), PromotionFixture.MD());
    }

    public static ProductSnapshot COCA_COLA_SNAPSHOT(final int quantity) {
        return COCA_COLA(quantity).getSnapshot();
    }
}
