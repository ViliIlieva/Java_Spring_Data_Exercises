package _01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _01Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();




        entityManager.getTransaction().commit();
        entityManager.close();
    }

}