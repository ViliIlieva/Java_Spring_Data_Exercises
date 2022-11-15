package com.example.gamestore.domain.dtos;

import com.example.gamestore.domain.entities.Game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.gamestore.constants.Validations.*;
import static com.example.gamestore.constants.Validations.*;

public class GameToEditDTO {
    private Long id;
    private String title;
    private String trailerId;
    private String thumbnailUrl;
    private float size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameToEditDTO(){}

    public GameToEditDTO(Long id, String title, String trailerId, String thumbnailUrl, float size, BigDecimal price, String description, LocalDate releaseDate) {
        setId(id);
        setTitle(title);
        setTrailerId(trailerId);
        setThumbnailUrl(thumbnailUrl);
        setSize(size);
        setPrice(price);
        setDescription(description);
        setReleaseDate(releaseDate);
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null &&
                Character.isLowerCase (title.charAt (0)) ||
                title.length () < 3 || title.length () > 100) {
            throw new IllegalArgumentException (NOT_VALID_GAME_TITLE);
        }
        this.title = title;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        if (trailerId != null && trailerId.length () != 11) {
            throw new IllegalArgumentException (TRAILER_ID_SHOULD_BE_11);
        }
        this.trailerId = trailerId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        Pattern pattern = Pattern.compile ("^(http://|https://).+");
        Matcher matcher = pattern.matcher (thumbnailUrl);
        if( !matcher.matches ()){
            throw new IllegalArgumentException (LINK_SHOULD_BEGIN_WITH_HTTP);
        }
        this.thumbnailUrl = thumbnailUrl;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        if (size <= 0.0) {
            throw new IllegalArgumentException (SIZE_SHOULD_BE_POSITIVE);
        }
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price != null && price.longValue () <= 0) {
            throw new IllegalArgumentException (PRICE_SHOULD_BE_POSITIVE);
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null && description.length () < 20){
            throw new IllegalArgumentException (DESCRIPTION_SHOULD_BE_MIN_20_CHARACTERS);
        }
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Game toGame(){
        return new Game (id, title, trailerId, thumbnailUrl, size, price, description, releaseDate);
    }
}
