package com.example.xml_exercise_cardealer.carDealer.config;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class BeanConfiguration {

    @Bean
    public Gson gson() {
        JsonDeserializer<LocalDateTime> toLocalDate =
                (json, t, c) -> LocalDateTime.parse(json.getAsString());

        JsonSerializer<String> fromLocalDate =
                (date, t, c) -> new JsonPrimitive (date);

        return new GsonBuilder ()
                .registerTypeAdapter(LocalDateTime.class, toLocalDate)
                .registerTypeAdapter(LocalDateTime.class, fromLocalDate)
                .setPrettyPrinting()
                .create();

    }
}