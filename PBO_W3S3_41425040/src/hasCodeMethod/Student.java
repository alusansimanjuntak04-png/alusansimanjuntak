public class Student {

    // Variabel untuk menyimpan nomor roll
    int roll;

    // Method hashCode() mengembalikan nilai roll sebagai hash code
    @Override
    public int hashCode() {
        return roll;
    }

    // Method utama program
    public static void main(String[] args) {

        // Membuat objek Student pertama
        Student s1 = new Student();

        // Mengisi roll Student pertama
        s1.roll = 101;

        // Membuat objek Student kedua
        Student s2 = new Student();

        // Mengisi roll Student kedua
        s2.roll = 102;

        // Menampilkan hash code Student pertama
        System.out.println("HashCode s1: " + s1.hashCode());

        // Menampilkan hash code Student kedua
        System.out.println("HashCode s2: " + s2.hashCode());
    }
}