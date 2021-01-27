package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//saved just as an example
//@Component
public class PersonDAOplainJDBC {

    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/people",
                    "stas", "stas");
            if (con != null) {
                System.out.println("connection ok");
            } else {
                System.out.println("couldn't establish connection");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM person ORDER BY name ASC";
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                Person person = new Person();

                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setAge(rs.getInt("age"));

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                person = new Person();
                person.setName(rs.getString("name"));
                person.setId(rs.getInt("id"));
                person.setAge(rs.getInt("age"));
                person.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO person (name, age, email) VALUES(?, ?, ?)");
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Person updatedPerson) {
        try {
            PreparedStatement ps = con.prepareStatement("update person set name = ?," +
                    "age = ?, email = ? where id = ?");
            ps.setString(1, updatedPerson.getName());
            ps.setInt(2, updatedPerson.getAge());
            ps.setString(3, updatedPerson.getEmail());
            ps.setInt(4, updatedPerson.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM person WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
