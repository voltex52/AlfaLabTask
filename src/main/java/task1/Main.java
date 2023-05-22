package task1;

import task1.entity.Document;
import task1.entity.Person;
import static task1.dao.PersonDao.savePerson;
import static task1.dao.PersonDao.showCustomPerson;

public class Main {
    public static void main(String[] args) {

        Person person = new Person();
        person.setFullName("Ivan Ivanov");
        person.setAge(30);

        Document document1 = new Document();
        document1.setNumber("123456");
        document1.setActive(true);
        document1.setPerson(person);

        Document document2 = new Document();
        document2.setNumber("789777");
        document2.setActive(true);
        document2.setPerson(person);

        person.getDocuments().add(document1);
        person.getDocuments().add(document2);

        savePerson(person);

        showCustomPerson();
    }

}
