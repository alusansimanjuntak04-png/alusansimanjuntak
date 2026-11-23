import java.util.*; /

/*
  Modul PBO W9S2 - Bagian 1: String Manipulation (KODE MODIFIKASI)
  Nama : Alusan Simanjuntak
  NIM  : 41425040
 
 
  1. Mengapa startsWith("Hello") mengembalikan false?
  ->  String text didefinisikan "  Hello World! ..." yaitu diawali DUA SPASI.
      Method startsWith() membandingkan karakter mulai dari indeks 0 secara persis
      (exact match, case-sensitive) dan spasi dihitung sebagai karakter. Karena
      karakter pertama adalah spasi (bukan 'H'), hasilnya false. Hal yang sama
      terjadi pada endsWith("Programming") karena ada dua spasi di akhir.
      Solusi: text.trim().startsWith("Hello") atau text.startsWith("Hello", 2).
      (Demo: lihat bagian "DEMO P1" pada main.)
 
  2. Apa perbedaan antara String dan StringBuilder?
  ->  - String bersifat IMMUTABLE (tidak dapat diubah; setiap modifikasi membuat
        objek baru), sedangkan StringBuilder bersifat MUTABLE (isi diubah langsung
        pada objek yang sama).
      - Untuk modifikasi berulang (mis. penyambungan dalam loop), StringBuilder
        jauh lebih efisien karena tidak membuat banyak objek sementara.
      - StringBuilder punya append(), insert(), reverse(), delete(); String punya
        trim(), substring(), replace(), split(), dll.
      - String aman untuk thread (karena immutable); StringBuilder tidak thread-safe
        (alternatifnya StringBuffer).
      (Demo: lihat bagian "DEMO P2" pada main.)

  3.  Bagaimana cara mengambil hanya kata "Java" dari string tersebut?
  ->  Cara 1: substring() dengan posisi dari indexOf("Java") dan panjang 4.
      Cara 2: split() lalu ambil elemen indeks ke-4 (kata ke-5) dari array words.
      (Demo: lihat bagian "DEMO P3" pada main.)
 
 Modifikasi 
   1. Menampilkan hanya kata dengan panjang lebih dari 4 karakter.
   2. Method countVowels() untuk menghitung jumlah huruf vokal dalam string.
 */

public class StringManipulationModifikasi {

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
        System.out.println("Starts with 'Hello': " + text.startsWith("Hello"));       // false: ada 2 spasi di awal (lihat J1)
        System.out.println("Ends with 'Programming': " + text.endsWith("Programming")); // false: ada 2 spasi di akhir

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

        // ===================== DEMO JAWABAN PERTANYAAN =====================

        // DEMO P1: membuktikan penyebab startsWith() bernilai false
        System.out.println("\n=== DEMO P1: STARTSWITH ===");
        System.out.println("text.startsWith(\"Hello\")           : " + text.startsWith("Hello"));          // false (ada spasi di awal)
        System.out.println("text.trim().startsWith(\"Hello\")    : " + text.trim().startsWith("Hello"));   // true (spasi sudah dibuang)
        System.out.println("text.startsWith(\"Hello\", 2)        : " + text.startsWith("Hello", 2));       // true (mulai cek dari indeks 2)

        // DEMO P2: String (immutable) vs StringBuilder (mutable)
        System.out.println("\n=== DEMO P2: STRING vs STRINGBUILDER ===");
        String s = "Java";
        s.concat(" Programming");        // Hasil concat() dibuang -> s TIDAK berubah (immutable)
        System.out.println("String setelah concat() tanpa assignment : " + s);
        s = s.concat(" Programming");    // Harus ditampung ke variabel (objek String baru dibuat)
        System.out.println("String setelah di-assign ulang            : " + s);
        StringBuilder sb2 = new StringBuilder("Java");
        sb2.append(" Programming");      // Objek yang sama langsung berubah (mutable)
        System.out.println("StringBuilder setelah append()            : " + sb2);

        // DEMO P3: mengambil hanya kata "Java"
        System.out.println("\n=== DEMO P3: AMBIL KATA 'Java' ===");
        int start = text.indexOf("Java");                                // Posisi awal kata "Java"
        String java = text.substring(start, start + "Java".length());    // Potong sepanjang 4 karakter
        System.out.println("Cara 1 (indexOf + substring): " + java);
        System.out.println("Cara 2 (split, indeks ke-4)  : " + words[4]); // "Java" berada di indeks 4 pada array words

        // TUGAS MODIFIKASI 

        // MODIFIKASI 1: tampilkan hanya kata dengan panjang > 4 karakter
        System.out.println("\n=== MODIFIKASI 1: KATA DENGAN PANJANG > 4 ===");
        System.out.print("Words (> 4 chars): ");
        for (String word : words) {
            if (word.length() > 4) {                // Filter (penyaringan): hanya kata dengan panjang > 4
                System.out.print("[" + word + "] ");
            }
        }
        System.out.println();

        // MODIFIKASI 2: hitung jumlah huruf vokal dengan method countVowels()
        System.out.println("\n=== MODIFIKASI 2: JUMLAH HURUF VOKAL ===");
        System.out.println("Jumlah huruf vokal: " + countVowels(text));
    }
    
    

    /*
     Menghitung jumlah huruf vokal (a, i, u, e, o) dalam sebuah string.
     @param s string masukan (input)
     @return jumlah huruf vokal (bertipe int)
    */
    
    public static int countVowels(String s) {
        int count = 0;                              // Variabel penghitung (counter), awalnya 0
        String vowels = "aiueoAIUEO";               // Himpunan huruf vokal (huruf kecil dan kapital)
        for (char c : s.toCharArray()) {            // Ubah string menjadi array char lalu iterasi satu per satu
            if (vowels.indexOf(c) != -1) {          // indexOf() != -1 artinya karakter ada di himpunan vokal
                count++;                            // Tambah counter jika karakter adalah vokal
            }
        }
        return count;                               // Kembalikan hasil perhitungan
    }
}
