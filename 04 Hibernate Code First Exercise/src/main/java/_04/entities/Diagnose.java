package _04.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "_04_diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String comment;

    @ManyToMany(mappedBy = "diagnose")
    private Set<Patient> patients;

    public Diagnose() {
    }

    public Diagnose(String name) {
        this.name = name;
        this.patients = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Patient> getPatients() {
        return Collections.unmodifiableSet(patients);
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
    }

}
