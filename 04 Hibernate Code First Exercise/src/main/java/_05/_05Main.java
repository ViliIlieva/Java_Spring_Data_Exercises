package _05;

import _05.entities.BankAccount;
import _05.entities.BillingDetails;
import _05.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _05Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User("Ivan", "Ivanov","ivan@abv.bg","12345");
        entityManager.persist(user);
        BankAccount account = new BankAccount("6789", user, "Fibank", "FIB");
        entityManager.persist(account);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
