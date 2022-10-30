import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _03_ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] employeeName = scanner.nextLine().split(" ");


        Long employeeCount = entityManager.createQuery(
                        "select count(e) from Employee e" +
                                " where e.firstName = :first_name" +
                                " and e.lastName = :last_name",
                        Long.class)
                .setParameter("first_name", employeeName[0])
                .setParameter("last_name", employeeName[1])
                .getSingleResult();

        if(employeeCount != 0){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
    }
}
