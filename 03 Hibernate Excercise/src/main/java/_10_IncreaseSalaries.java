import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        String department1 = "Engineering";
        String department2 = "Tool Design";
        String department3 = "Marketing";
        String department4 = "Information Services";

        List<Employee> employees = entityManager.createQuery(
                        "from Employee e" +
                                " where e.department.name = : dep1" +
                                " or e.department.name = : dep2" +
                                " or e.department.name = : dep3" +
                                " or e.department.name = :dep4", Employee.class)
                .setParameter("dep1", department1)
                .setParameter("dep2", department2)
                .setParameter("dep3", department3)
                .setParameter("dep4", department4)
                .getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
            System.out.printf("%s %s ($%.2f)\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
