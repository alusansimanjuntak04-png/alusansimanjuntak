public class Geeks {

    public static void main(String[] args) {

        // Membuat object Geeks
        Geeks t = new Geeks();

        // Menampilkan hash code object
        System.out.println(t.hashCode());

        // Menghapus referensi object
        t = null;

        // Meminta Java menjalankan Garbage Collector
        System.gc();

        // Menampilkan pesan akhir
        System.out.println("end");
    }

    // Method finalize() dipanggil sebelum object dibersihkan
    @Override
    protected void finalize() {
        System.out.println("finalize method called");
    }
}