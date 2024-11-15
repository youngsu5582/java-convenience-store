package store.purchase;

import store.product.ProductSnapshot;

import java.math.BigDecimal;

public record PurchaseInfo(ProductSnapshot productSnapshot, int count, PurchaseType purchaseType) {
    public boolean isPurchase() {
        return purchaseType == PurchaseType.BUY || purchaseType == PurchaseType.PROMOTION_BUY;
    }

    public boolean isOnlyBuy() {
        return purchaseType == PurchaseType.BUY;
    }

    public boolean isPromotion() {
        return purchaseType == PurchaseType.PROMOTION_GIVE;
    }

    public BigDecimal getMoney() {
        return productSnapshot.price()
                .multiply(BigDecimal.valueOf(count));
    }
}
