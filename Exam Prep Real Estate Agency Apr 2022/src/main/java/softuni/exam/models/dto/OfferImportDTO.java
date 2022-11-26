package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDTO {

    @XmlElement
    @Positive
    @NotNull
     public BigDecimal price;

    @XmlElement
    @NotNull
    private AgentNameDTO agent;

    @XmlElement
    @NotNull
    private ApartmentIdDTO apartment;

    @XmlElement
    private String publishedOn;



}
