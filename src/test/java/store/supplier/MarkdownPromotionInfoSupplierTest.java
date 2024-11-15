package store.supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.promotion.Promotions;

import static org.assertj.core.api.Assertions.assertThat;

class MarkdownPromotionInfoSupplierTest {
    @Test
    @DisplayName("파일에서 값을 불러온다.")
    void read_with_file() {
        final MarkdownPromotionInfoSupplier supplier = new MarkdownPromotionInfoSupplier("src/test/resources/promotions.md");
        final Promotions promotions = supplier.getPromotions();
        assertThat(promotions.promotions()).hasSize(3);
    }
}
