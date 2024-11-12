package store.product;

import store.promotion.Promotion;

public record PromotionProduct(Product product, Promotion promotion) {

    public int checkPromotionCount(final int quantity) {
        return promotion.checkCount(product.getQuantity(quantity));
    }

    public int applyPromotionCount(final int quantity) {
        return promotion.applyCount(product.getQuantity(quantity));
    }

    public String getName() {
        return product.getName();
    }

    public PromotionProduct minus(final int quantity) {
        return new PromotionProduct(product.minus(quantity), promotion);
    }

    public ProductSnapshot getSnapshot() {
        return new ProductSnapshot(product.getName(), product.getPrice(), promotion.getName());
    }
}
