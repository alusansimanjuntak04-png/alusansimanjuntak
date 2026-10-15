public class Geeks {

    // Method utama
    public static void main(String[] args) {

        // Membuat object String
        Object o = new String("GeeksForGeeks");

        // Mengambil class dari object
        Class c = o.getClass();

        // Menampilkan nama class object
        System.out.println("Class of Object o is: "
                + c.getName());
    }
}