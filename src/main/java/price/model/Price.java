package price.model;

import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Price {
    @Getter @Setter
    private Long id;
    @Getter @Setter @NonNull
    private String productCode;
    @Getter @Setter @NonNull
    private Integer number;
    @Getter @Setter @NonNull
    private Integer depart;
    @Getter @Setter @NonNull
    private DateTime begin;
    @Getter @Setter @NonNull
    private DateTime end;
    @Getter @Setter @NonNull
    private Long value;

    public Price(Price price) {
        this.productCode = price.productCode;
        this.number = price.number;
        this.depart = price.depart;
        this.begin = price.begin;
        this.end = price.end;
        this.value = price.value;
    }

    public boolean isDateIntersectWith(Price price) {
        return end.isAfter(price.getBegin()) && begin.isBefore(price.getEnd());
    }

    public boolean equalsProductNumberDepart(Price price) {
        return productCode.equals(price.productCode) && number.equals(price.number) && depart.equals(price.depart);
    }
}