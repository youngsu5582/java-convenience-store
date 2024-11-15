package store.fixture;

import store.promotion.Promotion;

import java.time.LocalDate;

//@formatter:off
public class PromotionFixture {
    public static Promotion TWO_PLUS_ONE() {
        return new Promotion(
                "탄산2+1",
                2,
                1,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1)
        );
    }
    public static Promotion MD(){
        return new Promotion(
                "MD추천상품",
                1,
                1,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1)
        );
    }
}
//@formatter:on
