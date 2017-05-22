package org.cct.DataBase;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by taioyui on 09/03/16.
 */
public class DataBase {
    //create an empty Vector employeesList of type Person
    Vector<Person> employeesList = new Vector<Person>();
    String separator = ",";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataBase newBase = new DataBase();
        newBase.readList();
        newBase.mainMenu();
    }

    public void createNewPerson() throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a first name: ");
        String firstName = checkInputForLetters();

        System.out.println("Please enter a last name: ");
        String lastName = checkInputForLetters();

        System.out.println("Please enter id: ");
        int id = checkInputDigits();

        System.out.println("Please enter a department: ");
        String department = checkInputForLetters();

        System.out.println("Please enter age: ");
        int age = checkInputDigits();

        //convert input department to lowercase and compare with "it"
        if (department.toLowerCase().equals("it")) {
            /*step-by-step object creation way
            ITperson iTperson = new ITperson(); ITperson values are passed as arguments to the constructor
            iTperson.setAge(age); iTperson object is created with them
            iTperson.setLastName(lastName);
            iTperson.setFirstName(firstName);
            iTperson.setId(id);
            iTperson.setDepartment(department);
            employeesList.add(iTperson); add object to vector */

            //add object to vector (simplified creation way)
            employeesList.add(new ITperson(firstName, lastName, age, id, department));
            System.out.println(firstName + " " + lastName + " " + age + " " + id + " " + department);

        } else if (department.toLowerCase().equals("finance")) {
            /*add object to vector(simplified creation way)
            BSperson bSperson = new BSperson(id,age,firstName,lastName,department);*/
            employeesList.add(new FINperson(firstName, lastName, age, id, department));
            System.out.println(firstName + " " + lastName + " " + age + " " + id + " " + department);
            System.out.println("press Enter for back to Menu");
        }else {
            System.out.println("wRONG DEPARTMENT, INPUT NOT ADDED");
        }writeCsvFile();
    }

    private void printMainMenu() {
        System.out.println("=======================================");
        System.out.println("|          MAIN MENU SELECTION        |");
        System.out.println("=======================================");
        System.out.println("| Options:                            |");
        System.out.println("|        1. Add new employee          |");
        System.out.println("|        2. Print list of employees   |");
        System.out.println("|        3. Exit                      |");
        System.out.println("=======================================");
        System.out.println("Please, make a selection [1-3]         ");
    }

    public void mainMenu() throws IOException, ClassNotFoundException {
        // set pick any value not equals exit choice
        int pick = 0;
        Scanner sc = new Scanner(System.in);
        while (pick != 3) {
            printMainMenu();
            pick = checkInputDigits();
            if (pick == 2) {
                SortMenu();
            } else if (pick == 1) {
                createNewPerson();
            } else if (pick == 3) {
                System.out.println("Bye Bye");
                System.exit(0);
            } else {
                System.out.println("Input digits 1-3");
            }
        }
    }

    public void SortMenu() throws IOException {
        // set pick any value not equals exit choice
        int pick = -1;
        Scanner sc = new Scanner(System.in);
        while (pick != 0) {
            printSortMenu();
            pick = checkInputDigits();
            if (pick == 1) {
                sortByFirstName();
                printList();
            } else if (pick == 2) {
                sortByLastName();
                printList();
            } else if (pick == 3) {
                sortByAge();
                printList();
            } else if (pick == 4) {
                sortByIdAscending();
                printList();
            } else if (pick == 5) {
                sortByIdDescending();
                printList();
            } else if (pick == 6) {
                sortByDepartment();
                printList();
            } else if (pick == 0) {
                printMainMenu();
            } else {
                System.out.println("Please, make a selection [0-6]");
            }
        }
    }

    public void printSortMenu() {
        System.out.println("====================================");
        System.out.println("|            SORT MENU             |");
        System.out.println("====================================");
        System.out.println("|     [1] Sort by first name       |");
        System.out.println("|     [2] Sort by last name        |");
        System.out.println("|     [3] Sort by age              |");
        System.out.println("|     [4] Sort by id descending    |");
        System.out.println("|     [5] Sort by id ascending     |");
        System.out.println("|     [6] Sort by department       |");
        System.out.println("====================================");
        System.out.println("Please, make a selection [0-6]");
        System.out.println("Press 0 for back to MAIM MENU");
    }

    public void readList() throws IOException {
        try{
            BufferedReader ordersReader = new BufferedReader(new FileReader("employees.log"));
            String line;
            ordersReader.readLine();
            while ((line = ordersReader.readLine()) != null) {
                //split string line to get fields without separator
                if (line.substring(line.lastIndexOf(separator) + 1).toLowerCase().equals("it")) {
                    employeesList.add(new ITperson(line, separator));
                } else {
                    employeesList.add(new FINperson(line, separator));
                }
            }
        }catch (Exception e) {
        System.out.println("FILE NOT FOUND");
        }
    }

    public int checkInputDigits() {//check input symbols are digits
        Scanner sc = new Scanner(System.in);
        Integer pick = null;//create empty object Integer type
        while (pick == null) {//do while object is empty
            try {
                String str = sc.nextLine();//read string from console
                pick = Integer.valueOf(str);//convert string to int
            } catch (Exception e) {
                System.out.println("Only digits allowed!");
            }
        }
        return pick;
    }

    public String checkInputForLetters() throws IOException, ClassNotFoundException {
        //scan string input
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //Create a Pattern object
        Pattern p = Pattern.compile("[a-zA-Z]+");
        //use to match the regular expression used to create it against different input
        while (!p.matcher(str).matches()) {//while str not (!) match pattern
            System.out.println("Should contain letters only!");
            str = sc.nextLine();
        }
        return str;
    }

    public void printList() throws IOException {
        Scanner scn = new Scanner(System.in);
      if (employeesList.size() == 0) {
            System.out.println("LIST IS EMPTY");
        }
        else {for (Person list : employeesList) {
                System.out.println(list.toString());
            }
            System.out.println("press any key for back to SORT MENU");
            scn.nextLine();//line for input enter (pause before read enter)
        }
    }

    public void writeCsvFile() throws IOException {
        String NEW_LINE_SEPARATOR = "\n";
        String FILE_HEADER = "first_name,last_name,age,id,department";
        FileWriter fileWriter = null;
        fileWriter = new FileWriter("employees.log");
        fileWriter.append(FILE_HEADER);
        fileWriter.append(NEW_LINE_SEPARATOR);
        for (Person person : employeesList) {
            fileWriter.append(person.toCSV(separator));
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        fileWriter.flush();
        fileWriter.close();
    }

    /*--- Start Sort using comparator and collections
    Compare 2 objects with arguments they both inherit from parent class*/
    class AgeComparator implements Comparator<Person> {
        public int compare(Person emp1, Person emp2) {
            int emp1Age = emp1.age;
            int emp2Age = emp2.age;
            return emp1Age - emp2Age;
        }
    }

    class LastNameComparator implements Comparator<Person> {
        public int compare(Person emp1, Person emp2) {
            return emp1.lastName.compareTo(emp2.lastName);
        }
    }

    class FirstNameComparator implements Comparator<Person> {
        public int compare(Person emp1, Person emp2) {
            return emp1.firstName.compareTo(emp2.firstName);
        }
    }

    class DepartmentComparator implements Comparator<Person> {
        public int compare(Person emp1, Person emp2) {
            //compare 2 objects (ITperson and FINperson)
            return emp1.getClass().toString().compareTo(emp2.getClass().toString());
        }
    }

    // Sorts the vector list using comparator
    public void sortByDepartment() {
        Collections.sort(employeesList, new DepartmentComparator());
    }

    public void sortByFirstName() {
        Collections.sort(employeesList, new FirstNameComparator());
    }

    public void sortByLastName() {
        Collections.sort(employeesList, new LastNameComparator());
    }

    public void sortByAge() {
        Collections.sort(employeesList, new AgeComparator());
    }
    //--- End Sort using comparator and collections

    //Bubble Sort
    public void sortByIdDescending() {
        for (int i = 1; i < employeesList.size(); i++) {
            for (int x = 0; x < employeesList.size() - 1; x++) {
                //compare elements, swap the elements
                //Repeat the same steps for vector[x] to vector[size-1]
                if (employeesList.elementAt(x).id < employeesList.elementAt(x + 1).id) {
                    Person temp2 = employeesList.elementAt(x);
                    employeesList.set(x, employeesList.elementAt(x + 1));
                    employeesList.set(x + 1, temp2);

                }

            }
        }
    }

    public void sortByIdAscending() {
        for (int i = 1; i < employeesList.size(); i++) {
            for (int x = 0; x < employeesList.size() - 1; x++) {
                if (employeesList.elementAt(x).id > employeesList.elementAt(x + 1).id) {
                    Person temp2 = employeesList.elementAt(x);
                    employeesList.set(x, employeesList.elementAt(x + 1));
                    employeesList.set(x + 1, temp2);

                }

            }
        }
    }
}






