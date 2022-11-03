package _04.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "_04_medicaments")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "medicament")
    private Set<Visitation> visitations;

    @ManyToMany(mappedBy = "medicament")
    private Set<Patient> patients;

    public Medicament(){
    }

    public Medicament(String name) {
        this.name = name;
        this.visitations = new HashSet<>();
        this.patients = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Visitation> getVisitations() {
        return Collections.unmodifiableSet(visitations);
    }

    public void addVisitations(Visitation visitation) {
        this.visitations.add(visitation);
    }

    public Set<Patient> getPatients() {
        return Collections.unmodifiableSet(patients);
    }

    public void addPatients(Patient patients) {
        this.patients.add(patients);
    }
}
