package store.purchase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.fixture.ProductFixture;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {

    Bill bill;

    @Test
    @DisplayName("")
    void get_purchase_money() {
        bill = new Bill(List.of(
                new PurchaseInfo(ProductFixture.COCA_COLA_SNAPSHOT(1), 3, PurchaseType.BUY)), true);

        assertThat(bill.getPurchaseMoney()).isEqualTo(new BigDecimal("3000"));
        assertThat(bill.getPurchaseInfos()).contains(new PurchaseInfo(ProductFixture.COCA_COLA_SNAPSHOT(1), 3, PurchaseType.BUY));
    }

    @Test
    @DisplayName("")
    void get_promotion_money() {
        bill = new Bill(List.of(
                new PurchaseInfo(ProductFixture.COCA_COLA_SNAPSHOT(1), 2, PurchaseType.BUY),
                new PurchaseInfo(ProductFixture.COCA_COLA_SNAPSHOT(1), 1, PurchaseType.PROMOTION_GIVE)), true);

        assertThat(bill.getPurchaseMoney()).isEqualTo(new BigDecimal("2000"));
        assertThat(bill.getPromotionMoney()).isEqualTo(new BigDecimal("1000"));
    }
}
