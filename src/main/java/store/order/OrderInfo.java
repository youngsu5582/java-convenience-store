package store.order;

public record OrderInfo(String productName, int count) {
    private static final String SEPARATOR = "-";

    public static OrderInfo from(final String line) {
        final String[] ary = line.split(SEPARATOR);
        return new OrderInfo(ary[0], Integer.parseInt(ary[1]));
    }

    public OrderInfo plus(final int count) {
        return new OrderInfo(productName, this.count + count);
    }

    public OrderInfo minus(final int count) {
        return new OrderInfo(productName, this.count - count);
    }
}
