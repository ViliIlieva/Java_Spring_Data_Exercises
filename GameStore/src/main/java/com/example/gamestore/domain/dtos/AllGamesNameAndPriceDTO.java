package com.example.gamestore.domain.dtos;

import java.math.BigDecimal;

public interface AllGamesNameAndPriceDTO {

    String getTitle();
    BigDecimal getPrice() ;

    default String info() {
        return getTitle() + " " +getPrice();
    }
}
