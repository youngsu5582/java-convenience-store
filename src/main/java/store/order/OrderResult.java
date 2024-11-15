package store.order;

public record OrderResult(String name, int quantity, ResultType resultType) {
    public boolean isSuccess() {
        return resultType == ResultType.NONE;
    }
}
