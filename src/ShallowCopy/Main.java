package ShallowCopy;

import java.util.*;

class Address {
    String name;

    Address(String name) {
        this.name = name;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    //clone method which is from the object class must be overridden
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("Punjab");
        Person person = new Person("unique", address);

        Person clonePerson = (Person) person.clone();

        clonePerson.address.name = "Haryana";

        System.out.println("person address: " + person.address.name);
        System.out.println("clone person address: " + clonePerson.address.name);

    }
}
