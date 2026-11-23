import java.util.*; 

/*
 Bagian 1: String Manipulation 
 Nama : Alusan Simanjuntak
 NIM  : 41425040
*/  

public class StringManipulation {

    // Entry point (titik masuk) eksekusi program Java
    public static void main(String[] args) {
        // Literal String dengan spasi di awal dan akhir (whitespace, karakter spasi kosong)
        String text = "  Hello World! Welcome to Java Programming  ";

        System.out.println("=== BASIC STRING OPERATIONS ===");
        System.out.println("Original: '" + text + "'");                  // Menampilkan string asli
        System.out.println("Length: " + text.length());                  // length() = jumlah karakter (panjang string)
        System.out.println("Trimmed: '" + text.trim() + "'");            // trim() = menghapus whitespace di awal/akhir
        System.out.println("Uppercase: '" + text.toUpperCase() + "'");   // toUpperCase() = ubah ke huruf kapital
        System.out.println("Lowercase: '" + text.toLowerCase() + "'");   // toLowerCase() = ubah ke huruf kecil

        // Substring dan indexing
        System.out.println("\n=== SUBSTRING & INDEXING ===");
        System.out.println("Character at index 5: '" + text.charAt(5) + "'");        // charAt(i) = karakter pada indeks ke-i (mulai dari 0)
        System.out.println("Substring (7-12): '" + text.substring(7, 12) + "'");      // substring(awal, akhir) = potongan [awal, akhir)
        System.out.println("Index of 'World': " + text.indexOf("World"));             // indexOf() = posisi kemunculan pertama
        System.out.println("Last index of 'a': " + text.lastIndexOf("a"));            // lastIndexOf() = posisi kemunculan terakhir

        // Replacement dan comparison
        System.out.println("\n=== REPLACEMENT & COMPARISON ===");
        System.out.println("Replace 'Java' with 'Python': '" +
                text.replace("Java", "Python") + "'");                                // replace() = mengganti semua kemunculan substring
        System.out.println("Contains 'Programming': " + text.contains("Programming")); // contains() = cek apakah mengandung substring
        System.out.println("Starts with 'Hello': " + text.startsWith("Hello"));       // startsWith() = cek awalan string
        System.out.println("Ends with 'Programming': " + text.endsWith("Programming")); // endsWith() = cek akhiran string

        // Split dan join
        System.out.println("\n=== SPLIT & JOIN ===");
        String[] words = text.trim().split(" ");        // trim() lalu split() berdasarkan spasi -> array kata
        System.out.println("Number of words: " + words.length); // Jumlah elemen array = jumlah kata
        System.out.print("Words: ");
        for (String word : words) {                     // Enhanced for-loop (iterasi setiap elemen array)
            System.out.print("[" + word + "] ");
        }
        System.out.println();

        // StringBuilder untuk efisiensi
        System.out.println("\n=== STRING BUILDER ===");
        StringBuilder sb = new StringBuilder();         // StringBuilder = string yang dapat diubah (mutable)
        sb.append("Java");                               // append() = tambah teks di akhir
        sb.append(" Programming");
        sb.insert(0, "Learn ");                          // insert(pos, teks) = sisipkan teks di posisi tertentu
        sb.reverse();                                    // reverse() = balik urutan karakter
        System.out.println("Reversed: " + sb.toString()); // toString() = konversi ke String
    }
}
