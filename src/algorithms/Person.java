package algorithms;

/**
 * Created by User on 24.04.2015.
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String fName, String lName, int ageInput) {
        firstName = fName;
        lastName = lName;
        age = ageInput;
    }

    public String getLastName() {
        return lastName;
    }

    public void displayPerson() {
        System.out.print("First Name: " + firstName);
        System.out.print(", Last Name: " + lastName);
        System.out.println(", Age: " + age);
    }
}
