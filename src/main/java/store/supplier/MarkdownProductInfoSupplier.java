package store.supplier;

import store.product.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

public class MarkdownProductInfoSupplier extends MarkdownSupplier implements ProductInfoSupplier {
    private static final String SEPARATOR = ",";
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final int PROMOTION_INDEX = 3;
    private static final String NOT_PROMOTION_KEYWORD = "null";

    public MarkdownProductInfoSupplier(final String filePath) {
        super(filePath);
    }

    @Override
    public List<ProductInfo> getProductInfo() {
        return readFileWithMarkdown()
                .stream()
                .map(this::parsePromotionInfo)
                .toList();
    }

    private ProductInfo parsePromotionInfo(final String line) {
        final String[] infos = line.split(SEPARATOR);
        return new ProductInfo(
                infos[NAME_INDEX],
                BigDecimal.valueOf(Integer.parseInt(infos[PRICE_INDEX])),
                Integer.parseInt(infos[QUANTITY_INDEX]),
                infos[PROMOTION_INDEX].equals(NOT_PROMOTION_KEYWORD) ? null : infos[PROMOTION_INDEX]
        );
    }
}
