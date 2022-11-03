package _04.core.interfaces;

import _04.entities.Diagnose;
import _04.entities.Medicament;
import _04.entities.Patient;

import java.io.IOException;
import java.time.LocalDate;

public interface Controller {
    String addPatient() throws IOException;
    String addDiagnose() throws IOException;
    String addMedicament() throws IOException;
    String addVisitation() throws IOException;
    LocalDate validateData();
    String validateName(String entity, String field) throws IOException;
    Patient searchPatientById() throws IOException;

    //    Class<?> searchById(String msg, Class<?> clazz) throws IOException;
    Diagnose searchDiagnoseById() throws IOException;
    Medicament searchMedicamentById() throws IOException;
    String searchVisitationForDate() throws IOException;
}
