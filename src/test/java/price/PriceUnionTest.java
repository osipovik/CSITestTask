package price;

import org.joda.time.DateTime;
import org.junit.Test;
import price.model.Price;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PriceUnionTest {

    PriceUnion priceUnion = new PriceUnion();

    @Test
    public void commonPriceUnionTest() {
        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "122856",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );
        oldPriceList.add(
                new Price(
                        "122856",
                        2,
                        1,
                        DateTime.parse("2017-01-10T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        99000L
                )
        );
        oldPriceList.add(
                new Price(
                        "6654",
                        1,
                        2,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T00:00:00"),
                        5000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "122856",
                        1,
                        1,
                        DateTime.parse("2017-01-20T00:00:00"),
                        DateTime.parse("2017-02-20T23:59:59"),
                        11000L
                )
        );
        newPriceList.add(
                new Price(
                        "122856",
                        2,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-25T23:59:59"),
                        92000L
                )
        );
        newPriceList.add(
                new Price(
                        "6654",
                        1,
                        2,
                        DateTime.parse("2017-01-12T00:00:00"),
                        DateTime.parse("2017-01-13T00:00:00"),
                        4000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "122856",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-02-20T23:59:59"),
                        11000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "122856",
                        2,
                        1,
                        DateTime.parse("2017-01-10T00:00:00"),
                        DateTime.parse("2017-01-15T00:00:00"),
                        99000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "122856",
                        2,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-25T23:59:59"),
                        92000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "6654",
                        1,
                        2,
                        DateTime.parse("2017-01-1T00:00:00"),
                        DateTime.parse("2017-01-12T00:00:00"),
                        5000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "6654",
                        1,
                        2,
                        DateTime.parse("2017-01-12T00:00:00"),
                        DateTime.parse("2017-01-13T00:00:00"),
                        4000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "6654",
                        1,
                        2,
                        DateTime.parse("2017-01-13T00:00:00"),
                        DateTime.parse("2017-01-31T00:00:00"),
                        5000L
                )
        );
        //endregion

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);

        assertEquals(expectedPriceList, unionPriceList);
    }

    @Test
    public void priceUnionWithUpdateEndDateTest() {

        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-20T00:00:00"),
                        DateTime.parse("2017-02-20T23:59:59"),
                        11000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-02-20T23:59:59"),
                        11000L
                )
        );
        //endregion

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);

        assertEquals(expectedPriceList, unionPriceList);
    }

    @Test
    public void newPriceInTheMiddleTest() {

        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-15T00:00:00"),
                        11000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-20T23:59:59"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );
        //endregion

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);

        assertEquals(expectedPriceList, unionPriceList);
    }

    @Test
    public void priceUnionWithDifferentDepartTest() {

        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        2,
                        DateTime.parse("2017-01-10T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        2,
                        DateTime.parse("2017-01-10T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );
        //endregion

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);

        assertEquals(expectedPriceList, unionPriceList);
    }

    @Test
    public void priceUnionWithDifferentNumberTest() {

        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "123",
                        2,
                        1,
                        DateTime.parse("2017-01-10T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        11000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        2,
                        1,
                        DateTime.parse("2017-01-10T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );
        //endregion

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);

        assertEquals(expectedPriceList, unionPriceList);
    }

    @Test
    public void newPriceOnIntersectOfTwoOldTest() {

        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        11000L
                )
        );
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-20T23:59:59"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        12000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-25T23:59:59"),
                        15000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-15T00:00:00"),
                        11000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-25T23:59:59"),
                        15000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-25T23:59:59"),
                        DateTime.parse("2017-01-31T23:59:59"),
                        12000L
                )
        );
        //endregion

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);

        assertEquals(expectedPriceList, unionPriceList);
    }

    @Test
    public void priceUnionOneMoreTest() {
        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-10T23:59:59"),
                        11000L
                )
        );
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-10T23:59:59"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-20T23:59:59"),
                        DateTime.parse("2017-01-31T00:00:00"),
                        13000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-08T00:00:00"),
                        DateTime.parse("2017-01-15T00:00:00"),
                        12000L
                )
        );
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-25T00:00:00"),
                        14000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-08T00:00:00"),
                        11000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-08T00:00:00"),
                        DateTime.parse("2017-01-15T00:00:00"),
                        12000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-15T00:00:00"),
                        DateTime.parse("2017-01-25T00:00:00"),
                        14000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-25T00:00:00"),
                        DateTime.parse("2017-01-31T00:00:00"),
                        13000L
                )
        );
        //endregion

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);

        assertEquals(expectedPriceList, unionPriceList);
    }

    @Test
    public void priceUnionLastForTodayTest() {
        //region Set up lists
        List<Price> oldPriceList = new LinkedList<>();
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-10T23:59:59"),
                        11000L
                )
        );
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-10T23:59:59"),
                        DateTime.parse("2017-01-20T23:59:59"),
                        15000L
                )
        );
        oldPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-20T23:59:59"),
                        DateTime.parse("2017-01-31T00:00:00"),
                        13000L
                )
        );

        List<Price> newPriceList = new LinkedList<>();
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-08T00:00:00"),
                        DateTime.parse("2017-01-13T00:00:00"),
                        12000L
                )
        );
        newPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-18T00:00:00"),
                        DateTime.parse("2017-01-25T00:00:00"),
                        14000L
                )
        );

        List<Price> expectedPriceList = new LinkedList<>();
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-01T00:00:00"),
                        DateTime.parse("2017-01-08T00:00:00"),
                        11000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-08T00:00:00"),
                        DateTime.parse("2017-01-13T00:00:00"),
                        12000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-13T00:00:00"),
                        DateTime.parse("2017-01-18T00:00:00"),
                        15000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-18T00:00:00"),
                        DateTime.parse("2017-01-25T00:00:00"),
                        14000L
                )
        );
        expectedPriceList.add(
                new Price(
                        "123",
                        1,
                        1,
                        DateTime.parse("2017-01-25T00:00:00"),
                        DateTime.parse("2017-01-31T00:00:00"),
                        13000L
                )
        );
        //endregion

        PriceComparator comparator = new PriceComparator();

        List<Price> unionPriceList = priceUnion.union(oldPriceList, newPriceList);
        unionPriceList.sort(comparator);
        expectedPriceList.sort(comparator);

        assertEquals(expectedPriceList, unionPriceList);
    }

    private static class PriceComparator implements Comparator<Price> {

        @Override
        public int compare(Price o1, Price o2) {
            if (!o1.getProductCode().equals(o2.getProductCode())) {
                return o1.getProductCode().compareTo(o2.getProductCode());
            } else if (!o1.getNumber().equals(o2.getNumber())) {
                return o1.getNumber().compareTo(o2.getNumber());
            } else if (!o1.getDepart().equals(o2.getDepart())) {
                return o1.getDepart().compareTo(o2.getDepart());
            } else if (!o1.getBegin().equals(o2.getBegin())) {
                return o1.getBegin().compareTo(o2.getBegin());
            } else if (!o1.getEnd().equals(o2.getEnd())) {
                return o1.getEnd().compareTo(o2.getEnd());
            }

            return o1.getValue().compareTo(o2.getValue());
        }
    }
}