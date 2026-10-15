
public class Geeks {

    static String name;
    static float price;

    // Method untuk mengatur nilai name dan price
    static void set(String n, float p) {
        name = n;
        price = p;
    }

    // Method untuk menampilkan nilai name dan price
    static void get() {
        System.out.println("Software name is: " + name);
        System.out.println("Software price is: " + price);
    }

    public static void main(String[] args) {
        Geeks.set("Visual Studio", 0.0f);
        Geeks.get();
    }
}