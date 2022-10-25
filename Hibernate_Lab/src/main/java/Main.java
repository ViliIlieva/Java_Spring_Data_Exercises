import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Student student = new Student("Emo");
        entityManager.persist(student);

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
