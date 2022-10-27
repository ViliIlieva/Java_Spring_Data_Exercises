package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school-db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //Student student = new Student("Teo");
        //em.persist(student);

        Student found = em.find(Student.class, 1);
        System.out.println(found.getId() + " " + found.getName());

        //em.remove(found);

        em.getTransaction().commit();
        em.close();

    }
}
