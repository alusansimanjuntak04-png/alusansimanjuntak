```java
class Vehicle {

    // Constructor Vehicle
    Vehicle() {
        System.out.println("This is a Vehicle");
    }
}

// Turunan dari Vehicle
class FourWheeler extends Vehicle {

    // Constructor FourWheeler
    FourWheeler() {
        System.out.println("4 Wheeler Vehicles");
    }
}

// Turunan dari FourWheeler
class Car extends FourWheeler {

    // Constructor Car
    Car() {
        System.out.println("This 4 Wheeler Vehicle is a Car");
    }
}

// Class utama
public class Geeks {

    public static void main(String[] args) {

        // Membuat objek Car
        Car obj = new Car();
    }
}
```
