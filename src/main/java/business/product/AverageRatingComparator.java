package business.product;

import java.util.Comparator;

public class AverageRatingComparator implements Comparator<Dish> {
    @Override
    public int compare(Dish obj1, Dish obj2) {
        return Double.compare(obj1.getAverageFeedBackRating(), obj2.getAverageFeedBackRating());
    }
}