package com.example.xml_exercise_cardealer.carDealer.constants;

import java.math.BigDecimal;
import java.util.Random;

public enum Discounts {
    ZERO (BigDecimal.ZERO),
    FIVE (BigDecimal.valueOf(0.05)),
    TEN (BigDecimal.valueOf(0.1)),
    FIFTEEN (BigDecimal.valueOf(0.15)),
    TWENTY (BigDecimal.valueOf(0.20)),
    THIRTY (BigDecimal.valueOf(0.30)),
    FORTY (BigDecimal.valueOf(0.40)),
    FIFTY (BigDecimal.valueOf(0.5));

    public final BigDecimal discountValue;
    private static final Random random = new Random();

    Discounts(BigDecimal discountValue){
        this.discountValue = discountValue;
    }


    public static Discounts getRandomDiscount(){
        Discounts[] discounts = values();
        return discounts[random.nextInt(discounts.length)];
    }

}
