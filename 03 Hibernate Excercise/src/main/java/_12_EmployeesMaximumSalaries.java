import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _12_EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

       entityManager.createQuery(
               "select e.department.name, MAX(e.salary) from Employee e" +
                       " group by e.department.name" +
                       " having MAX(e.salary) not between 30000 and 70000",Object[].class)
                       .getResultList()
                               .forEach(object -> System.out.println(object[0] + " " + object[1]));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

