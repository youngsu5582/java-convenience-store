package store.stock;

public record StockInfo(int promotionCount, int productCount) {
    public boolean lessThan(final int count) {
        return promotionCount < count;
    }

    public boolean greaterThan(final int count) {
        return promotionCount > count;
    }

    public boolean hasProduct() {
        return productCount > 0;
    }
}
