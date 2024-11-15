package store.promotion;

import java.util.Map;

public record Promotions(Map<String, Promotion> promotions) {

    public Promotion getPromotion(final String promotionName) {
        return promotions.get(promotionName);
    }

}
