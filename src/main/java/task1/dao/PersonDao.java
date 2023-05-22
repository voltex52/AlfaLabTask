package task1.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import task1.entity.Person;
import java.util.List;
import static task1.util.Util.getSessionFactory;

public class PersonDao {

    public static void savePerson(Person person) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
        session.close();
    }

    public static void showCustomPerson() {
        Session session = getSessionFactory().openSession();

        String hql = "SELECT p.fullName, p.age, d.number "
                + "FROM Person p "
                + "JOIN p.documents d "
                + "WHERE d.number LIKE '%777%' "
                + "AND d.active = true";

        Query<Object[]> query = session.createQuery(hql);
        List<Object[]> results = query.list();

        for (Object[] result : results) {
            String fullName = (String) result[0];
            int age = (int) result[1];
            String documentNumber = (String) result[2];
            System.out.println(fullName + " " + age + ", " + documentNumber);
        }

        session.close();
    }



}
