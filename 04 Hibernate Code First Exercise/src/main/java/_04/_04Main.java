package _04;

import _04.core.ControllerImpl;
import _04.core.EngineImpl;
import _04.core.interfaces.Controller;
import _04.io.ConsoleReader;
import _04.io.ConsoleWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _04Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        Controller controller = new ControllerImpl(reader, writer, entityManager);

        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
