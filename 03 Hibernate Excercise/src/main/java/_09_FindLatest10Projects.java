import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class _09_FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("from Project p " +
                        "order by p.startDate desc, p.name", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("Project name: %s\n        " +
                                "Project Description: %s\n        " +
                                "Project Start Date: %s\n        " +
                                "Project End Date: %s\n",
                        p.getName(), p.getDescription(),
                        p.getStartDate().minusHours(3)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")),
                        p.getEndDate()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


