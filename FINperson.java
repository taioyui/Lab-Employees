package org.cct.DataBase;

/**
 * Created by taioyui on 09/03/16.
 */
public class FINperson extends Person {
    String department;

    public FINperson() {

    }

    public FINperson(String firstName, String lastName, int age, int id, String department) {
        super(id, age, firstName, lastName);
        this.department = department;
    }

    public FINperson(String csv, String separator) {
        super();
        String[] fields = csv.split(separator);
        this.firstName = fields[0];
        this.lastName = fields[1];
        this.age = Integer.valueOf(fields[2]);
        this.id = Integer.valueOf(fields[3]);
        this.department = fields[4];
    }


    @Override
    public String toCSV(String separator) {
        return firstName + separator + lastName + separator + age + separator + id + separator + department;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("First name: ").append(String.format("%-10s", firstName)).append("Last name: ").
                append(String.format("%-10s", lastName)).append("Age: ").append(String.format("%-10s", age)).append("ID: ").
                append(String.format("%-10s", id)).append("Department: ").append(department).toString();
    }

}
