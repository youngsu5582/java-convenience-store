package store.purchase;

import java.math.BigDecimal;

public class Counter {

    private static final BigDecimal MEMBER_SHIP_MAX_MONEY = BigDecimal.valueOf(8000);
    private static final BigDecimal MEMBER_SHIP_RATIO = BigDecimal.valueOf(0.3);

    private final BigDecimal memberShipMaxMoney;
    private final BigDecimal memberShipRatio;

    public Counter() {
        this.memberShipMaxMoney = MEMBER_SHIP_MAX_MONEY;
        this.memberShipRatio = MEMBER_SHIP_RATIO;
    }

    public Counter(final BigDecimal memberShipMaxMoney, final double memberShipRatio) {
        this.memberShipMaxMoney = memberShipMaxMoney;
        this.memberShipRatio = BigDecimal.valueOf(memberShipRatio);
    }

    public Receipt purchase(final Bill bill) {
        if (bill.isApplyMembership()) {
            final BigDecimal purchaseMoney = bill.getPurchaseMoney();
            final BigDecimal membershipDiscount = calculateMembershipDiscount(purchaseMoney);
            return new Receipt(bill, membershipDiscount, memberShipRatio);
        }
        return new Receipt(bill, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    private BigDecimal calculateMembershipDiscount(final BigDecimal purchaseMoney) {
        final BigDecimal discount = purchaseMoney.multiply(MEMBER_SHIP_RATIO);
        return discount.min(memberShipMaxMoney);
    }
}

