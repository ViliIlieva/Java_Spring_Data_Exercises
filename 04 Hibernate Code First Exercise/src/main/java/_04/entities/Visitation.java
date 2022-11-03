package _04.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "_04_visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    @Column(length = 1000)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;

    @ManyToMany
    @JoinTable(name = "_04_visitations_medicaments",
            joinColumns = @JoinColumn(name = "visitation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> medicament;

    public Visitation() {
    }

    public Visitation(LocalDate date, String comment, Patient patient, Diagnose diagnose, Set<Medicament> medicaments) {
        this.date = date;
        this.medicament = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Set<Medicament> getMedicament() {
        return Collections.unmodifiableSet(medicament);
    }

    public void addMedicament(Medicament medicament) {
        this.medicament.add(medicament);
    }

    @Override
    public String toString() {
        return "Преглед " + id + "/" + date + System.lineSeparator() +
                "Пациент: " + patient.getFirstName() + " " +patient.getLastName() + System.lineSeparator() +
                "Поставена диагноза: " + diagnose.getName() + System.lineSeparator() +
                "Предписани медикаменти: " + medicament.stream().map(Medicament::getName).collect(Collectors.joining(", ")) ;
    }
}

