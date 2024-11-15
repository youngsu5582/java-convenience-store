package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.fixture.ProductFixture;
import store.fixture.PromotionFixture;
import store.order.OrderInfo;
import store.purchase.PurchaseInfo;
import store.purchase.PurchaseType;
import store.stock.Stock;
import store.stock.StockInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StockTest {

    @Test
    @DisplayName("프로모션 조건에 맞게 개수를 반환한다.")
    void return_promotion_check() {
        final Stock stock = new Stock();
        stock.addProduct(ProductFixture.COCA_COLA(7));
        stock.addProduct(ProductFixture.COCA_COLA(7), PromotionFixture.TWO_PLUS_ONE());

        final StockInfo info = stock.getInfo(new OrderInfo("콜라", 7));
        assertThat(info.productCount()).isEqualTo(1);
        assertThat(info.promotionCount()).isEqualTo(6);
    }

    @Test
    @DisplayName("구매 개수 보다 프로모션 개수가 더 많을시 많게 반환한다.")
    void return_promotion_is_large() {
        final Stock stock = new Stock();
        stock.addProduct(ProductFixture.ORANGE_JUICE(9), PromotionFixture.MD());

        final StockInfo info = stock.getInfo(new OrderInfo("오렌지주스", 1));
        assertThat(info.promotionCount()).isEqualTo(2);
        assertThat(info.productCount()).isZero();
    }

    @Test
    @DisplayName("프로모션 개수가 없으면 이에 맞게 반환한다.")
    void return_product_if_not_exist_promotion() {
        final Stock stock = new Stock();
        stock.addProduct(ProductFixture.ORANGE_JUICE(9));

        final StockInfo info = stock.getInfo(new OrderInfo("오렌지주스", 3));
        assertThat(info.promotionCount()).isZero();
        assertThat(info.productCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("결제를 한다.")
    void purchase_case1() {
        final Stock stock = new Stock();
        stock.addProduct(ProductFixture.COCA_COLA(7));
        stock.addProduct(ProductFixture.COCA_COLA(7), PromotionFixture.TWO_PLUS_ONE());

        final List<PurchaseInfo> purchaseInfos = stock.purchaseWithPromotion("콜라", 6);
        assertThat(purchaseInfos).hasSize(2)
                .containsExactlyInAnyOrder(new PurchaseInfo(ProductFixture.COLA_TWO_PLUS_ONE(7)
                        .getSnapshot(), 4, PurchaseType.PROMOTION_BUY), new PurchaseInfo(ProductFixture.COLA_TWO_PLUS_ONE(7)
                        .getSnapshot(), 2, PurchaseType.PROMOTION_GIVE));
    }

    @Test
    @DisplayName("결제를 한다.")
    void purchase_case2() {
        final Stock stock = new Stock();
        stock.addProduct(ProductFixture.ORANGE_JUICE(2), PromotionFixture.MD());

        final List<PurchaseInfo> purchaseInfos = stock.purchaseWithPromotion("오렌지주스", 1);
        assertThat(purchaseInfos).hasSize(1)
                .containsExactlyInAnyOrder(
                        new PurchaseInfo(ProductFixture.ORANGE_JUICE_MD(7).getSnapshot(), 1, PurchaseType.PROMOTION_BUY));
    }

    @Test
    @DisplayName("결제를 한다.")
    void purchase_case3() {
        final Stock stock = new Stock();
        stock.addProduct(ProductFixture.COCA_COLA(7));

        final PurchaseInfo purchaseInfo = stock.purchase("콜라", 6);
        assertThat(purchaseInfo.count()).isEqualTo(6);
        assertThat(purchaseInfo.productSnapshot()).isEqualTo(
                ProductFixture.COCA_COLA(7)
                        .getSnapshot()
        );
    }
}
