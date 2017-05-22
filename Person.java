package org.cct.DataBase;

/**
 * Created by taioyui on 09/03/16.
 */
public abstract class Person {
    int age;
    int id;
    String firstName;
    String lastName;

    public Person(int age, int id, String firstName, String lastName) {
        this.age = age;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public Person(int age, int id) {
        this.age = age;
        this.id = id;
    }

    public abstract String toCSV(String separator);

    public abstract String toString();


}


