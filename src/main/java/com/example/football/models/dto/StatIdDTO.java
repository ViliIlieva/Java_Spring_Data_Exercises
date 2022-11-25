package com.example.football.models.dto;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class StatIdDTO {

    @XmlElement
    private Long id;
}
