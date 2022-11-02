package _05.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "CARD")
public class CreditCard extends BillingDetails{

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "expiration_month", nullable = false)
    private LocalDate ExpirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private LocalDate ExpirationYear;

    public CreditCard() {
    }

    public CreditCard(String number, User user, String cardType, LocalDate expirationMonth, LocalDate expirationYear) {
        super(number, user);

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

    public LocalDate getExpirationMonth() {
        return ExpirationMonth;
    }

    public void setExpirationMonth(LocalDate expirationMonth) {
        ExpirationMonth = expirationMonth;
    }

    public LocalDate getExpirationYear() {
        return ExpirationYear;
    }

    public void setExpirationYear(LocalDate expirationYear) {
        ExpirationYear = expirationYear;
    }
}
