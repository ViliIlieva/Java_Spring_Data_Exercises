import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _08_GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        int employeeID = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.createQuery("from Employee e" +
                        " where e.id = :inputId", Employee.class)
                .setParameter("inputId", employeeID)
                .getSingleResult();
        System.out.printf("%s %s - %s\n      ", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        String projects = employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .map(Project::getName)
                .collect(Collectors.joining("\n      "));
        System.out.println(projects);

        entityManager.getTransaction().commit();
    }
}
