package com.example.gamestore.constants;

import com.example.gamestore.domain.dtos.GameDTO;

import javax.xml.stream.events.Characters;

public enum Validations {
    ;
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    public static final String EMAIL_NOT_VALID_MESSAGE = "Incorrect email.";

    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
    public static final String PASSWORD_NOT_VALID_MESSAGE = "Incorrect username / password";
    public static final String PASSWORDS_MISS_MATCH_MESSAGE = "Passwords are not matching";
    public static final String LOGOUT_NOT_LOGGED_IN_USER = "Cannot log out. No user was logged in.";

    public static final String NOT_VALID_GAME_TITLE = "Not a valid game title.";
    public static final String PRICE_SHOULD_BE_POSITIVE = "Price should be positive number.";
    public static final String SIZE_SHOULD_BE_POSITIVE = "Size should be positive number.";
    public static final String TRAILER_ID_SHOULD_BE_11 = "Trailer ID should be exactly 11.";
    public static final String LINK_SHOULD_BEGIN_WITH_HTTP = "Link should begin with http ...";
    public static final String DESCRIPTION_SHOULD_BE_MIN_20_CHARACTERS = "Description should be at least 20 characters.";

    public static final String IMPOSSIBLE_COMMAND = "Impossible command";



}