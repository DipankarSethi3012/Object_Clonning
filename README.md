# Object Cloning in Java

This project demonstrates two fundamental object cloning techniques in Java: **Shallow Copy** and **Deep Copy**.  
It also explains the use of **Marker Interfaces** (`Cloneable`) and how to use **Reflection APIs** to check marker interfaces at runtime.

---

## 📁 Folder Structure

- **ShallowCopy/Main.java** – Demonstrates shallow cloning.  
- **DeepCopy/Main.java** – Demonstrates deep cloning.  
- **BothCopyExample/Main.java** – Shows side-by-side comparison of shallow vs deep copy.  

---

# Object Cloning in Java: Shallow Copy

This section explains **Shallow Copy** in Java, a fundamental concept when working with objects and cloning.

---

## 📌 What is Shallow Copy?

A *shallow copy* creates a new object whose **primitive fields** are copied as-is, but **object references are shared** between the original and the cloned object.  

- **Primitive fields (int, float, char, etc.)** → copied  
- **Reference fields (objects, arrays, lists, etc.)** → **shared**, not cloned  

> **Important:** If the referenced object is mutable, changes in one object will affect the other.

---

## 🛠️ Example: Shallow Copy

```java
class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow clone
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Delhi");
        Person p1 = new Person("Ravi", addr);
        Person p2 = (Person) p1.clone(); // shallow copy

        // Modify cloned object's address
        p2.address.city = "Mumbai";

        System.out.println("Original Person Address: " + p1.address.city); // Mumbai
        System.out.println("Cloned Person Address: " + p2.address.city);   // Mumbai
    }
}
```
**Explanation:**
* p1 and p2 share the same Address object.
* Changing p2.address.city also affects p1.address.city.
* This is the classic behavior of shallow copy with mutable reference fields.
  
```
Shallow Copy Diagram
    +----------------+
Original |   Person       |
         | name: Ravi     |
         | address: Delhi |
         +----------------+
               |
        Shallow Copy
               |
         +----------------+
Clone    |   Person       |
         | name: Ravi     |
         | address: Delhi | <-- SAME OBJECT
         +----------------+

```

**Takeaway:**

* Shallow copy is fast and low memory but has shared references.
* Use it only when shared references are acceptable.
  
**⚡ Key Points**
* Shallow copy copies primitive fields but shares reference fields.
* Changing mutable objects in the clone affects the original.
* Implement the Cloneable marker interface to allow Object.clone() to work.

*Useful for simple objects or immutable references.*

# Object Cloning in Java: Deep Copy

This section explains **Deep Copy** in Java, which creates a fully independent clone of an object, including all objects referenced by it.

---

## 📌 What is Deep Copy?

A *deep copy* creates a new object and **recursively clones all objects referenced by its fields**.  

- **Primitive fields (int, float, char, etc.)** → copied  
- **Reference fields (objects, arrays, lists, etc.)** → **new objects**, not shared  

> **Important:** Changes in the cloned object **do NOT affect the original**, even for mutable references.

---

## 🛠️ Example: Deep Copy

```java
class Address implements Cloneable {
    String city;
    Address(String city) { this.city = city; }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Deep clone
    public Object clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone(); // shallow copy first
        cloned.address = (Address) address.clone(); // deep copy of Address
        return cloned;
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Delhi");
        Person p1 = new Person("Ravi", addr);
        Person p2 = (Person) p1.clone(); // deep copy

        // Modify cloned object's address
        p2.address.city = "Mumbai";

        System.out.println("Original Person Address: " + p1.address.city); // Delhi
        System.out.println("Cloned Person Address: " + p2.address.city);   // Mumbai
    }
}
```

**Explanation:**
* p1 and p2 have different Address objects.
* Changing p2.address.city does not affect p1.address.city.
* Deep copy ensures complete independence between original and clone.

```
  Deep Copy Diagram
   +----------------+
  Original |   Person       |
         | name: Ravi     |
         | address: Delhi |
         +----------------+
               |
         Deep Copy
               |
         +----------------+
    Clone    |   Person       |
         | name: Ravi     |
         | address: Delhi | <-- NEW OBJECT
         +----------------+
```
# Object Cloning in Java: Marker Interface & Reflection API

This section explains how **Marker Interfaces** work in Java, specifically `Cloneable`, and how **Reflection API** can be used to check for them at runtime.

---

## 📌 Marker Interface

A **marker interface** is an interface that **does not declare any methods**.  
It is used to **mark classes** with a special property that the JVM or frameworks can recognize.

### Example: `Cloneable`

- Indicates that a class supports **safe cloning** using `Object.clone()`.  
- If a class does **not implement `Cloneable`** and `clone()` is called, **`CloneNotSupportedException`** is thrown.

```java
class Person {
    String name;
}

Person p = new Person();
p.clone(); // Throws CloneNotSupportedException
```
* Implementing Cloneable fixes this:
```
class Person implements Cloneable {
    String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```
