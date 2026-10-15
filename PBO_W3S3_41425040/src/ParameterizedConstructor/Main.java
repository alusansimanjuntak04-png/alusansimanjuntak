class Geeks {

    // Data member untuk menyimpan nama dan ID
    String name;
    int id;

    // Constructor dengan parameter
    Geeks(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

// Class utama
public class Main {

    // Method utama
    public static void main(String[] args) {

        // Membuat object dengan constructor berparameter
        Geeks geek1 = new Geeks("Sweta", 68);

        // Menampilkan nama dan ID
        System.out.println("GeekName: " + geek1.name
                + " and GeekId: " + geek1.id);
    }
}