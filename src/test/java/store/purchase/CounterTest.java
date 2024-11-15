package store.purchase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.fixture.ProductFixture;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    Counter counter = new Counter(BigDecimal.valueOf(8000),30);

    @Test
    @DisplayName("최대 멤버십 비율은 30%이다.")
    void some(){
        final Bill bill = new Bill(
                List.of(new PurchaseInfo(ProductFixture.COCA_COLA_SNAPSHOT(1), 10, PurchaseType.BUY)),
                true
        );
        final Receipt receipt = counter.purchase(bill);
        assertThat(receipt.membershipDiscount()).isEqualTo(BigDecimal.valueOf(3000.0));
    }


    @Test
    @DisplayName("최대 금액은 8000원이다.")
    void max_discount_is_8000(){
        final Bill bill = new Bill(
                List.of(new PurchaseInfo(ProductFixture.COCA_COLA_SNAPSHOT(1), 10000, PurchaseType.BUY)),
                true
        );
        final Receipt receipt = counter.purchase(bill);
        assertThat(receipt.membershipDiscount()).isEqualTo(BigDecimal.valueOf(8000));
    }

    @Test
    @DisplayName("멤버십을 적용하지 않으면 0원이다.")
    void return_zero_not_apply_membership(){
        final Bill bill = new Bill(
                List.of(new PurchaseInfo(ProductFixture.COCA_COLA_SNAPSHOT(1), 10000, PurchaseType.BUY)),
                false
        );
        final Receipt receipt = counter.purchase(bill);
        assertThat(receipt.membershipDiscount()).isEqualTo(BigDecimal.ZERO);
    }
}
