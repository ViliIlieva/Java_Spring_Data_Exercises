package _05.entities;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;

@Entity
@DiscriminatorValue(value = "CARD")
public class CreditCard extends BillingDetails{

    private static final String TYPE = "CARD";

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "expiration_month", nullable = false)
    private Month ExpirationMonth;

    @Column(name = "expiration_year", nullable = false, length = 4)
    private Year ExpirationYear;

    public CreditCard() {
    }

    public CreditCard(String number, User user, String cardType, Month expirationMonth, Year expirationYear) {
        super(number, user, TYPE);

        this.cardType = cardType;
        ExpirationMonth = expirationMonth;
        ExpirationYear = expirationYear;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getExpirationMonth() {
        return ExpirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.ExpirationMonth = expirationMonth;
    }

    public Year getExpirationYear() {
        return ExpirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.ExpirationYear = expirationYear;
    }


}
