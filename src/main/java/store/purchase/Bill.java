package store.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bill {

    private final List<PurchaseInfo> purchaseInfos;
    private boolean isMembership;

    public Bill() {
        this.purchaseInfos = new ArrayList<>();
        this.isMembership = false;
    }

    public Bill(final List<PurchaseInfo> purchaseInfos, final boolean isMembership) {
        this.purchaseInfos = purchaseInfos;
        this.isMembership = isMembership;
    }

    public void applyMemberShip(final boolean flag) {
        isMembership = flag;
    }

    public boolean isApplyMembership(){
        return isMembership;
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

    public BigDecimal getBuyMoney() {
        return purchaseInfos.stream()
                .filter(PurchaseInfo::isOnlyBuy)
                .map(PurchaseInfo::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getPurchaseMoney() {
        return purchaseInfos.stream()
                .filter(PurchaseInfo::isPurchase)
                .map(PurchaseInfo::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getPromotionMoney() {
        return purchaseInfos.stream()
                .filter(PurchaseInfo::isPromotion)
                .map(PurchaseInfo::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<PurchaseInfo> getPurchaseInfos() {
        return purchaseInfos.stream()
                .filter(PurchaseInfo::isPurchase)
                .toList();
    }

    public List<PurchaseInfo> getPromotionInfos() {
        return purchaseInfos.stream()
                .filter(PurchaseInfo::isPromotion)
                .toList();
    }
}
