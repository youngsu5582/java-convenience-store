package store.promotion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionTest {

    @Test
    @DisplayName("갯수중 몇개가 프로모션으로 적용되는지 알려준다.")
    void apply_count_case1() {
        final Promotion promotion = new Promotion("1+1", 1, 1, LocalDate.now()
                .minusDays(1), LocalDate.now()
                .plusDays(1));

        final int count = promotion.checkCount(1);
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("갯수중 몇개가 프로모션으로 적용되는지 알려준다.")
    void apply_count_case2() {
        final Promotion promotion = new Promotion("2+1", 2, 1, LocalDate.now()
                .minusDays(1), LocalDate.now()
                .plusDays(1));

        final int count = promotion.checkCount(7);
        assertThat(count).isEqualTo(6);
    }
}
