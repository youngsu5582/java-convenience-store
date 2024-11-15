package store;

import org.junit.jupiter.api.Test;
import store.fixture.ProductFixture;
import store.fixture.PromotionFixture;
import store.order.OrderInfo;
import store.order.OrderResult;
import store.order.ResultType;
import store.product.Product;
import store.product.Products;
import store.product.PromotionProduct;
import store.product.PromotionProducts;
import store.purchase.Bill;
import store.stock.Stock;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ConvenientTest {
    @Test
    void check_case1() {
        final Convenient convenient = new Convenient(
                new Stock(
                        new Products(
                                Map.of("콜라", ProductFixture.COCA_COLA(10))
                        ),
                        new PromotionProducts(
                                Map.of("콜라", new PromotionProduct(ProductFixture.COCA_COLA(7), PromotionFixture.TWO_PLUS_ONE()))
                        )
                )
        );

        final OrderResult result = convenient.check(new OrderInfo("콜라", 10));
        assertThat(result).isEqualTo(new OrderResult("콜라", 4, ResultType.PROMOTION_STOCK_LACK));
    }

    @Test
    void check_case2() {
        final Convenient convenient = new Convenient(
                new Stock(
                        new Products(),
                        new PromotionProducts(
                                Map.of("오렌지주스", new PromotionProduct(ProductFixture.ORANGE_JUICE(10), PromotionFixture.MD()))
                        )
                )
        );

        final OrderResult result = convenient.check(new OrderInfo("오렌지주스", 1));
        assertThat(result).isEqualTo(new OrderResult("오렌지주스", 1, ResultType.ADDITION_PROMOTION));
    }

    @Test
    void purchase_case1() {
        final PromotionProduct cola = new PromotionProduct(ProductFixture.COCA_COLA(7), PromotionFixture.TWO_PLUS_ONE());
        final Convenient convenient = new Convenient(
                new Stock(
                        new Products(
                                Map.of()
                        ),
                        new PromotionProducts(
                                Map.of("콜라", cola))
                )
        );

        final Bill bill = convenient.purchase(new OrderInfo("콜라", 3));
        assertThat(bill.getPurchaseMoney()).isEqualTo(BigDecimal.valueOf(2000));
    }

    @Test
    void purchase_case2() {
        final Product cola = ProductFixture.COCA_COLA(7);
        final Convenient convenient = new Convenient(
                new Stock(
                        new Products(
                                Map.of("콜라", cola)
                        ),
                        new PromotionProducts(
                        )
                ));

        final Bill bill = convenient.purchase(new OrderInfo("콜라", 3));
        assertThat(bill.getPurchaseMoney()).isEqualTo(BigDecimal.valueOf(3000));
    }


}
