package price;

import price.model.Price;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PriceUnion {

    public List<Price> union(List<Price> oldPriceList, List<Price> newPriceList) {
        List<Price> unionPriceList = new LinkedList<>(oldPriceList);

        for (Price newPrice : newPriceList) {
            unionPriceList = getChangedPriceListByNewPrice(unionPriceList, newPrice);
        }

        return unionPriceList;
    }

    private List<Price> getChangedPriceListByNewPrice(List<Price> oldPriceList, Price newPrice) {
        List<Price> unionPriceList = new LinkedList<>(oldPriceList);

        List<Price> intersectList = oldPriceList
                .stream()
                .filter(price -> price.equalsProductNumberDepart(newPrice) && price.isDateIntersectWith(newPrice))
                .collect(Collectors.toList());
        unionPriceList.removeAll(intersectList);

        if (intersectList.isEmpty()) {
            unionPriceList.add(newPrice);
        } else {
            unionPriceList.addAll(setNewPriceDatesByNewPrice(intersectList, newPrice));
        }

        return unionPriceList;
    }

    private List<Price> setNewPriceDatesByNewPrice(List<Price> intersectList, Price newPrice) {
        List<Price> unionPriceList = new LinkedList<>();

        intersectList.forEach(oldPrice -> {
            if (oldPrice.getValue().equals(newPrice.getValue())) {
                if (oldPrice.getEnd().isBefore(newPrice.getEnd())) {
                    Price price = new Price(oldPrice);
                    price.setEnd(newPrice.getEnd());
                    unionPriceList.add(price);
                }
            } else {
                if (oldPrice.getBegin().isBefore(newPrice.getBegin())) {
                    Price price = new Price(oldPrice);
                    price.setEnd(newPrice.getBegin());
                    unionPriceList.add(price);
                }

                if (!unionPriceList.contains(newPrice)) {
                    unionPriceList.add(newPrice);
                }

                if (oldPrice.getEnd().isAfter(newPrice.getEnd())) {
                    Price price = new Price(oldPrice);
                    price.setBegin(newPrice.getEnd());
                    unionPriceList.add(price);
                }
            }
        });

        return unionPriceList;
    }
}
