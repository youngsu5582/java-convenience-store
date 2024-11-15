package store.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.fixture.ProductFixture;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    @DisplayName("자기보다 적은 개수는 그대로 반환한다.")
    void return_original() {
        Product product = ProductFixture.COCA_COLA(3);
        assertThat(product.getQuantity(2)).isEqualTo(2);
    }

    @Test
    @DisplayName("자기보다 큰 개수는 자기 개수를 반환한다.")
    void return_quantity() {
        Product product = ProductFixture.COCA_COLA(3);
        assertThat(product.getQuantity(4)).isEqualTo(3);
    }
}
