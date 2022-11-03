package _04.entities;

import org.hibernate.type.ImageType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "_04_patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String address;

    @Column(unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private ImageType picture;

    @Column(nullable = false)
    private boolean insurance;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;

    @ManyToMany
    @JoinTable(name = "_04_patient_diagnoses",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
    private Set<Diagnose> diagnose;

    @ManyToMany
    @JoinTable(name = "_04_patient_medicament",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> medicament;

    public Patient() {
    }

    public Patient(String firstName, String lastName, LocalDate birthDate, boolean insurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.insurance = insurance;
        this.visitations = new HashSet<>();
        this.diagnose = new HashSet<>();
        this.medicament = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ImageType getPicture() {
        return picture;
    }

    public void setPicture(ImageType picture) {
        this.picture = picture;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public Set<Visitation> getVisitation() {
        return Collections.unmodifiableSet(visitations);
    }

    public void addVisitation(Visitation visitation) {
        this.visitations.add(visitation);
    }

    public Set<Diagnose> getDiagnose() {
        return Collections.unmodifiableSet(diagnose);
    }

    public void addDiagnose(Diagnose diagnoses) {
        this.diagnose.add(diagnoses);
    }

    public Set<Medicament> getMedicament() {
        return Collections.unmodifiableSet(medicament);
    }

    public void addMedicament(Medicament medicaments) {
        this.medicament.add(medicaments);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + System.lineSeparator() +
                "постоянен адрес: " + (address == null ? "не е въведен" : address) + System.lineSeparator() +
                "e-mail: " + (email == null ? "не е въведен" : email) + System.lineSeparator() +
                "роден на: " + birthDate + System.lineSeparator() +
                "осигурен: " + (insurance ? "да" : "не") + System.lineSeparator() +
                "брой прегледи: " + visitations.size();
    }
}
