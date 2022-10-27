import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

    //    Student example = new Student();
    //    example.setName("Tosho");
    //    session.persist(example);
    //    Student fromDb = session.get(Student.class, 2);
    //    System.out.println(fromDb.getId() + " " + fromDb.getName());

        List<Student> students =
                session.createQuery("From Student as s WHERE s.name = 'Gosho'", Student.class).list();
        //полето в базата е first_name но в java класа е просто name и го викаме в заявката
        //с името от java
        for (Student student : students) {
            System.out.println(student.getId() + " " + student.getName());
        }

        session.getTransaction().commit();
        session.close();


    }
}
