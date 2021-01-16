package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT = 0;

    {
        people = new ArrayList<>();

        people.add(new Person(PEOPLE_COUNT++, "Tom", "Soier", "ts@mail.com"));
        people.add(new Person(PEOPLE_COUNT++, "Kate", "Winslet", "kw@mail.com"));
        people.add(new Person(PEOPLE_COUNT++, "Sam", "Winchester", "sw@noghost.com"));
        people.add(new Person(PEOPLE_COUNT++, "Dean", "Winchester", "dw@noghost.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        //retrieving person with queried id
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
