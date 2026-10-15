```java
class Vehicle {

    // Constructor Vehicle
    Vehicle() {
        System.out.println("This is a Vehicle");
    }
}

// Subclass Car
class Car extends Vehicle {

    // Constructor Car
    Car() {
        System.out.println("This Vehicle is Car");
    }
}

// Subclass Bus
class Bus extends Vehicle {

    // Constructor Bus
    Bus() {
        System.out.println("This Vehicle is Bus");
    }
}

// Class utama
public class Test {

    public static void main(String[] args) {

        // Membuat objek Car
        Car obj1 = new Car();

        // Membuat objek Bus
        Bus obj2 = new Bus();
    }
}
```
