package algorithms;

/**
 * Created by User on 24.04.2015.
 */
public class ArrayInsObject {
    private Person[] persons;
    private int nElems;

    public ArrayInsObject(int size) {
        persons = new Person[size];
        nElems = 0;
    }

    public void insert(String first, String last, int age) {
        persons[nElems] = new Person(first, last, age);
        nElems++;
    }

    public void display()
    {
        for(int i = 0; i < nElems; i++)
            persons[i].displayPerson();
        System.out.println(" ");
    }

    public void insertionSort() {
        for(int i=1; i< nElems; i++) {
            int out = i;
            int in = out;
            Person temp = persons[i];
            while(in>0 && temp.getLastName().compareTo(persons[in-1].getLastName()) < 0) {
                persons[in] = persons[in-1];
                in--;
            }
            persons[in] = temp;
        }

    }
}
