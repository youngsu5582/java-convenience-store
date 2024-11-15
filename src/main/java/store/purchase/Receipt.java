package store.purchase;

import java.math.BigDecimal;

public record Receipt (Bill bill, BigDecimal membershipDiscount, BigDecimal membershipRatio){
}
