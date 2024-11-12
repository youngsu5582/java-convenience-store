package store.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record Bill(List<PurchaseInfo> purchaseInfos) {
    public Bill() {
        this(new ArrayList<>());
    }

    public void add(final PurchaseInfo purchaseInfo) {
        if (purchaseInfo == null) {
            return;
        }
        this.purchaseInfos.add(purchaseInfo);
    }

    public void add(final List<PurchaseInfo> purchaseInfos) {
        if (purchaseInfos == null) {
            return;
        }
        this.purchaseInfos.addAll(purchaseInfos);
    }

    public BigDecimal getPurchaseMoney() {
        return purchaseInfos.stream()
                .filter(PurchaseInfo::isPurchase)
                .map(PurchaseInfo::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
