class Geeks {

    // Constructor dengan satu parameter String
    Geeks(String name) {
        System.out.println("Constructor with one "
                + "argument - String: " + name);
    }

    // Constructor dengan dua parameter
    Geeks(String name, int age) {
        System.out.println("Constructor with two arguments: "
                + "String and Integer: " + name + " " + age);
    }

    // Constructor dengan satu parameter long
    Geeks(long id) {
        System.out.println("Constructor with one argument: "
                + "Long: " + id);
    }
}

// Class utama
public class Main {

    // Method utama
    public static void main(String[] args) {

        // Memanggil constructor String
        Geeks geek2 = new Geeks("Sweta");

        // Memanggil constructor String dan int
        Geeks geek3 = new Geeks("Amiya", 28);

        // Memanggil constructor long
        Geeks geek4 = new Geeks(325614567L);
    }
}