package store;

import store.order.OrderInfo;
import store.order.OrderResult;
import store.order.OrderResults;
import store.order.ResultType;
import store.purchase.Bill;
import store.stock.Stock;
import store.stock.StockInfo;

import java.util.List;

public class Convenient {
    private final Stock stock;

    public Convenient(final Stock stock) {
        this.stock = stock;
    }

    public OrderResults check(final List<OrderInfo> orders) {
        return new OrderResults(orders
                .stream()
                .map(this::check)
                .toList());
    }

    public OrderResult check(final OrderInfo orderInfo) {
        final StockInfo stockInfo = stock.getInfo(orderInfo);
        if (stockInfo.lessThan(orderInfo.count())) {
            return new OrderResult(orderInfo.productName(), orderInfo.count() - stockInfo.promotionCount(), ResultType.PROMOTION_STOCK_LACK);
        }
        if (stockInfo.greaterThan(orderInfo.count())) {
            return new OrderResult(orderInfo.productName(), stockInfo.promotionCount() - orderInfo.count(), ResultType.ADDITION_PROMOTION);
        }
        return new OrderResult(orderInfo.productName(), orderInfo.count(), ResultType.NONE);
    }

    public Bill purchase(final OrderInfo orderInfo) {
        final StockInfo stockInfo = stock.getInfo(orderInfo);
        final Bill bill = new Bill();
        bill.add(stock.purchaseWithPromotion(orderInfo.productName(), stockInfo.promotionCount()));
        bill.add(stock.purchase(orderInfo.productName(), stockInfo.productCount()));
        return bill;
    }
}
