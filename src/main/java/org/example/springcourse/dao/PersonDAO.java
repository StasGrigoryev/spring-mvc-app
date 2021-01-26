package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT = 1;

    {
        people = new ArrayList<>();

        people.add(new Person(PEOPLE_COUNT++, "Tom Noah", 12, "ts@mail.com"));
        people.add(new Person(PEOPLE_COUNT++, "Kate Cigarette", 30, "kw@mail.com"));
        people.add(new Person(PEOPLE_COUNT++, "Sam Van Haute", 25, "sw@noghost.com"));
        people.add(new Person(PEOPLE_COUNT++, "Dean Paradigm", 27, "dw@noghost.com"));
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

    public void update(int id, Person updatedPerson) {
        Person p = show(id);
        p.setId(updatedPerson.getId());
        p.setName(updatedPerson.getName());
        p.setAge(updatedPerson.getAge());
        p.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
