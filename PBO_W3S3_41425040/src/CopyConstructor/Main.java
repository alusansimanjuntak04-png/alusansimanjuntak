class Geeks {

    // Menyimpan nama dan ID
    String name;
    int id;

    // Parameterized Constructor
    Geeks(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Copy Constructor untuk menyalin object
    Geeks(Geeks obj2) {
        this.name = obj2.name;
        this.id = obj2.id;
    }
}

// Class utama
public class Main {

    // Method utama
    public static void main(String[] args) {

        // Membuat object pertama
        System.out.println("First Object");

        Geeks geek1 = new Geeks("Sweta", 68);

        // Menampilkan data object pertama
        System.out.println("GeekName: " + geek1.name
                + " and GeekId: " + geek1.id);

        System.out.println();

        // Membuat object kedua dengan Copy Constructor
        Geeks geek2 = new Geeks(geek1);

        // Menampilkan data object hasil copy
        System.out.println("Copy Constructor used Second Object");
        System.out.println("GeekName: " + geek2.name
                + " and GeekId: " + geek2.id);
    }
}