package DeepCopy;
class Address implements Cloneable{
    String city;

    Address(String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(this.city); //Creating a new Object
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone(); //shallow copy;
        //deep copy
        person.address = (Address) address.clone();
        return person;
    }
}
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("Punjab");

        Person person = new Person("Unique", address);

        Person clonedPerson = (Person) person.clone();

        clonedPerson.address.city = "Haryana";

        System.out.println("person city: " + person.address.city);
        System.out.println("clonedPerson: " + clonedPerson.address.city);
    }
}
