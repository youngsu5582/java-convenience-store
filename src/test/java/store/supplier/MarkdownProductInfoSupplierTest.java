package store.supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.product.ProductInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MarkdownProductInfoSupplierTest {
    @Test
    @DisplayName("파일에서 값을 불러온다.")
    void read_with_file() {
        final MarkdownProductInfoSupplier supplier = new MarkdownProductInfoSupplier("src/test/resources/products.md");
        final List<ProductInfo> productInfos = supplier.getProductInfo();
        assertThat(productInfos).hasSize(4);
    }

}
