```java
class Vehicle { // Super class

    // Constructor Vehicle
    Vehicle() {
        System.out.println("This is a Vehicle");
    }
}

// Subclass
class Car extends Vehicle {

    // Constructor Car
    Car() {
        System.out.println("This Vehicle is Car");
    }
}

// Class utama
public class Test {

    public static void main(String[] args) {

        // Membuat objek Car
        Car obj = new Car();
    }
}
```
