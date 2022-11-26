package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "published_on")
    private LocalDate publishedOn;

    @ManyToOne(optional = false)
    private Apartment apartment;

    @ManyToOne(optional = false)
    private Agent agent;

    public Offer(BigDecimal price, LocalDate publishedOn, Apartment apartment, Agent agent) {
        this.price = price;
        this.publishedOn = publishedOn;
        this.apartment = apartment;
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Agent " + agent.getFirstName () + " " + agent.getLastName () +
                " with offer №" + id  + System.lineSeparator () +
                "     -Apartment area: " + apartment.getArea ()  + System.lineSeparator () +
                "     --Town: " + apartment.getTown ().getTownName ()  + System.lineSeparator () +
                "     ---Price: " + price + "$";
    }

//    @Override
//    public String toString() {
//        return String.format(
//                "Agent %s %s with offer №%d:\n" +
//                        "\t-Apartment area: %.2f\n" +
//                        "\t--Town: %s\n" +
//                        "\t---Price: %.2f$\n",
//                this.getAgent().getFirstName(),
//                this.getAgent().getLastName(),
//                this.getId(),
//                this.getApartment().getArea(),
//                this.getApartment().getTown().getTownName(),
//                this.price);
}
