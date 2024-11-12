package store.stock;

import store.order.OrderInfo;
import store.product.Product;
import store.product.Products;
import store.product.PromotionProduct;
import store.product.PromotionProducts;
import store.promotion.Promotion;
import store.purchase.PurchaseInfo;
import store.purchase.PurchaseType;

import java.util.ArrayList;
import java.util.List;

public record Stock(Products products, PromotionProducts promotionProducts) {

    public Stock() {
        this(new Products(), new PromotionProducts());
    }

    public void addProduct(final Product product) {
        products.add(product);
    }

    public void addProduct(final Product product, final Promotion promotion) {
        promotionProducts.add(new PromotionProduct(product, promotion));
    }

    public StockInfo getInfo(final OrderInfo orderInfo) {
        final int promotionCount = promotionProducts.getQuantity(orderInfo);
        final int productCount = products.getQuantity(orderInfo.minus(promotionCount));
        return new StockInfo(promotionCount, productCount);
    }

    public PurchaseInfo purchase(final String productName, final int count) {
        if (count <= 0 || products.notContains(productName)) {
            return null;
        }
        final Product product = products.minus(productName, count);
        return new PurchaseInfo(product.getSnapshot(), count, PurchaseType.BUY);
    }

    public List<PurchaseInfo> purchaseWithPromotion(final String productName, final int count) {
        if (count <= 0 || products.notContains(productName)) {
            return List.of();
        }
        final int promotionCount = promotionProducts.applyCount(productName, count);
        final PromotionProduct promotionProduct = promotionProducts.minus(productName, count);
        return createPurchaseInfo(promotionProduct, promotionCount, count - promotionCount);
    }

    private List<PurchaseInfo> createPurchaseInfo(final PromotionProduct product, final int promotionCount, final int count) {
        final List<PurchaseInfo> purchaseInfos = new ArrayList<>();
        if (promotionCount > 0) {
            purchaseInfos.add(new PurchaseInfo(product.getSnapshot(), promotionCount, PurchaseType.PROMOTION));
        }
        if (count - promotionCount > 0) {
            purchaseInfos.add(new PurchaseInfo(product.getSnapshot(), count, PurchaseType.BUY));
        }
        return purchaseInfos;
    }
}
