package store.supplier;

import store.promotion.Promotion;
import store.promotion.Promotions;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MarkdownPromotionInfoSupplier extends MarkdownSupplier implements PromotionInfoSupplier {
    private static final String SEPARATOR = ",";
    private static final int NAME_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int GET_INDEX = 2;
    private static final int START_DATE_INDEX = 3;
    private static final int END_INDEX = 4;

    public MarkdownPromotionInfoSupplier(final String filePath) {
        super(filePath);
    }

    @Override
    public Promotions getPromotions() {
        final List<String> contents = readFileWithMarkdown();
        return new Promotions(contents.stream()
                .map(this::parsePromotion)
                .collect(Collectors.toMap(Promotion::getName, Function.identity())));
    }

    private Promotion parsePromotion(final String line) {
        final String[] infos = line.split(SEPARATOR);
        return new Promotion(
                infos[NAME_INDEX],
                Integer.parseInt(infos[BUY_INDEX]),
                Integer.parseInt(infos[GET_INDEX]),
                LocalDate.parse(infos[START_DATE_INDEX]),
                LocalDate.parse(infos[END_INDEX])
        );
    }
}
