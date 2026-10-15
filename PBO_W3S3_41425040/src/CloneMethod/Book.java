public class Book implements Cloneable {

    private String t;

    // Constructor untuk mengisi judul buku
    public Book(String t) {
        this.t = t;
    }

    // Membuat salinan object Book
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Mengambil judul buku
    public String getTitle() {
        return t;
    }

    // Method utama
    public static void main(String[] args) {

        try {
            // Membuat object buku pertama
            Book b1 = new Book("Java Basics");

            // Membuat clone dari b1
            Book b2 = (Book) b1.clone();

            // Menampilkan judul buku pertama
            System.out.println("Judul buku b1: " + b1.getTitle());

            // Menampilkan judul buku hasil clone
            System.out.println("Judul buku b2: " + b2.getTitle());

            // Mengecek apakah b1 dan b2 object yang sama
            System.out.println("Apakah b1 dan b2 objek yang sama? " + (b1 == b2));

        } catch (CloneNotSupportedException e) {

            // Menampilkan error jika clone gagal
            e.printStackTrace();
        }
    }
}