package implementationChange;

import java.util.List;

public class Price {
    private final int price;

    private Price(int price) {
        this.price = price;
    }

    public static Price sum(List<Price> prices) {
        return amount(prices.stream()
                .mapToInt(a -> a.price).sum());
    }

    public static Price amount(int price) {
        return new Price(price);
    }

    public int getPrice() {
        return price;
    }
}
