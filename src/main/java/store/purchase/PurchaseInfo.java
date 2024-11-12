package store.purchase;

import store.product.ProductSnapshot;

import java.math.BigDecimal;

public record PurchaseInfo(ProductSnapshot productSnapshot, int count, PurchaseType purchaseType) {
    public boolean isPurchase() {
        return purchaseType == PurchaseType.BUY;
    }

    public BigDecimal getMoney() {
        return productSnapshot.price().multiply(BigDecimal.valueOf(count));
    }
}
