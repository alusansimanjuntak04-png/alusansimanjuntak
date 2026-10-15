class ObjectPassDemo {

    // Variabel untuk menyimpan nilai
    int a, b;

    // Constructor untuk mengisi nilai a dan b
    ObjectPassDemo(int i, int j) {
        a = i;
        b = j;
    }

    // Membandingkan nilai a dan b dari dua object
    boolean equalTo(ObjectPassDemo o) {
        return (o.a == a && o.b == b);
    }
}

// Class utama
public class Main {

    // Method utama
    public static void main(String args[]) {

        // Membuat object pertama
        ObjectPassDemo ob1 = new ObjectPassDemo(100, 22);

        // Membuat object kedua
        ObjectPassDemo ob2 = new ObjectPassDemo(100, 22);

        // Membuat object ketiga
        ObjectPassDemo ob3 = new ObjectPassDemo(-1, -1);

        // Membandingkan object pertama dan kedua
        System.out.println("ob1 == ob2: "
                + ob1.equalTo(ob2));

        // Membandingkan object pertama dan ketiga
        System.out.println("ob1 == ob3: "
                + ob1.equalTo(ob3));
    }
}