// Class Geeks menggunakan Cloneable agar object bisa di-clone
class Geeks implements Cloneable {

    // Method clone() untuk membuat salinan object
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Mengambil clone dari parent class
    }

    // Menyimpan nama
    String name = "GeeksForGeeks";

    // Method utama program
    public static void main(String[] args) {

        // Membuat object Geeks
        Geeks o1 = new Geeks();

        try {
            // Membuat clone dari object o1
            Geeks o2 = (Geeks) o1.clone();

            // Menampilkan nama dari object hasil clone
            System.out.println(o2.name);

        } catch (CloneNotSupportedException e) {
            // Menampilkan error jika clone gagal
            e.printStackTrace();
        }
    }
}