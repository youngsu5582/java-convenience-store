package store;

import store.product.Product;
import store.product.ProductInfo;
import store.promotion.Promotions;
import store.stock.Stock;

import java.util.List;

public class ProductManagement {
    private final Promotions promotions;

    public ProductManagement(final Promotions promotions) {
        this.promotions = promotions;
    }

    public Stock manageStock(final List<ProductInfo> productInfos) {
        final Stock stock = new Stock();
        for (final ProductInfo productInfo : productInfos) {
            final Product product = new Product(productInfo);
            if (productInfo.isPromotion()) {
                stock.addProduct(product, promotions.getPromotion(productInfo.promotionName()));
                continue;
            }
            stock.addProduct(product);
        }
        return stock;
    }
}
