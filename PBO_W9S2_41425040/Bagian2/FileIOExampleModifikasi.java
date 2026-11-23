import java.util.*; // Import paket utilitas
import java.io.*;   // Import paket I/O (BufferedReader, BufferedWriter, FileReader, FileWriter, IOException)

/*
Bagian 2: Stream I/O Dasar (KODE MODIFIKASI)
Nama : Alusan Simanjuntak
NIM  : 41425040
 
 
  1.  Apa keuntungan menggunakan try-with-resources?
  ->  - Resource yang mengimplementasikan AutoCloseable (BufferedReader, BufferedWriter)
        OTOMATIS ditutup di akhir blok, baik program berjalan normal maupun terjadi
        exception.
      - Mencegah resource leak (file handle tidak tertutup); data di buffer writer
        pasti di-flush saat close().
      - Kode lebih ringkas dibanding try-finally manual; beberapa resource ditutup
        berurutan terbalik dari urutan pembukaannya.
 
  2. Mengapa perlu menangani FileNotFoundException dan IOException secara terpisah?
  ->  FileNotFoundException adalah SUBCLASS dari IOException. Dengan memisahkannya,
      program dapat memberi penanganan/pesan yang spesifik (file tidak ada / path
      salah) yang berbeda dari kegagalan I/O umum (gagal baca, disk error, permission).
      Urutan catch harus dari yang SPESIFIK ke yang UMUM; jika IOException ditulis
      lebih dulu, compiler menolak karena FileNotFoundException sudah tertangkap.
      (Demo: lihat "DEMO P2" pada main, membaca file yang tidak ada.)
 
  3. Apa yang terjadi jika file data.txt sudah ada sebelum program dijalankan?
  ->  new FileWriter(filename) tanpa argumen kedua berada pada mode OVERWRITE: isi
      lama file dihapus (truncate) lalu ditimpa data baru. Jika file belum ada, file
      dibuat baru. Untuk menambah tanpa menghapus, gunakan new FileWriter(filename, true)
      (mode append). Jika file ada tetapi tidak boleh ditulis (read-only / tanpa izin),
      terjadi IOException seperti "data.txt (Permission denied)".
      (Demo: lihat "DEMO P3" pada main.)

  
 
 TUGAS MODIFIKASI 
 1. Exception handling untuk NumberFormatException  -> processFile()
 2. Method untuk membaca file dan menghitung rata-rata usia -> calculateAverageAge()
 3. Menambahkan data baru ke file tanpa menghapus yang lama -> appendToFile()
 */

public class FileIOExampleModifikasi {

    // Entry point (titik masuk) program
    public static void main(String[] args) {
        String inputFile = "data.txt";     // Nama file sumber data
        String outputFile = "output.txt";  // Nama file hasil pemrosesan

        // Tulis data ke file (menimpa isi lama / mode overwrite)
        writeToFile(inputFile);

        // Baca data dari file
        readFromFile(inputFile);

        // Process dan tulis ke file baru
        processFile(inputFile, outputFile);

        // MODIFIKASI 2: hitung rata-rata usia
        calculateAverageAge(inputFile);

        // DEMO P2: membaca file yang tidak ada -> masuk ke catch FileNotFoundException
        System.out.println("\n=== DEMO P2: FILE TIDAK DITEMUKAN ===");
        readFromFile("file_tidak_ada.txt");

        // DEMO P3: data.txt sudah ada -> writeToFile() menimpa isi lama (overwrite)
        System.out.println("\n=== DEMO P3: OVERWRITE vs APPEND ===");
        writeToFile(inputFile);                       // Menimpa isi data.txt dengan 4 baris awal
        // MODIFIKASI 3: tambah data baru tanpa menghapus yang lama (mode append)
        appendToFile(inputFile, "Eka,27,Biology");
        // Baris sengaja tidak valid (usia bukan angka) untuk menguji NumberFormatException
        appendToFile(inputFile, "Fajar,abc,Biology");

        System.out.println("\n--- Setelah data ditambahkan (append) ---");
        readFromFile(inputFile);
        processFile(inputFile, outputFile);
        calculateAverageAge(inputFile);
    }

    /*
     Menulis data awal ke file.
     FileWriter(filename) tanpa argumen kedua = mode overwrite (isi lama dihapus) -> lihat J3.
    */
    public static void writeToFile(String filename) {
        // try-with-resources: writer otomatis di-close di akhir blok (lihat J1)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Alice,25,Computer Science"); // write() = tulis string ke buffer
            writer.newLine();                          // newLine() = tulis pemisah baris sesuai OS
            writer.write("Bob,30,Mathematics");
            writer.newLine();
            writer.write("Charlie,22,Physics");
            writer.newLine();
            writer.write("Diana,28,Chemistry");
            writer.newLine();

            System.out.println("Data berhasil ditulis ke " + filename);
        } catch (IOException e) { // Menangkap kegagalan I/O (mis. permission denied, disk penuh)
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    /*
     Membaca file baris demi baris dan menampilkannya dengan nomor baris.
    */
    public static void readFromFile(String filename) {
        System.out.println("\n=== MEMBACA FILE ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;            // Menampung satu baris hasil pembacaan
            int lineNumber = 1;     // Penomoran baris (dimulai dari 1)

            // readLine() mengembalikan null saat End of File (akhir file) tercapai
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }

            System.out.println("File berhasil dibaca");
        } catch (FileNotFoundException e) {
            // Exception SPESIFIK (subclass IOException): file tidak ada -> harus ditulis lebih dulu (lihat J2)
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {
            // Exception UMUM: error I/O lainnya saat membaca
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /*
     Membaca inputFile, mengubah nama menjadi kapital, menentukan status, lalu menulis ke outputFile. MODIFIKASI 1: menambahkan penanganan NumberFormatException per baris.
    */
    public static void processFile(String inputFile, String outputFile) {
        System.out.println("\n=== MEMPROSES FILE ===");
        // Dua resource sekaligus dalam satu try-with-resources; keduanya otomatis ditutup
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            writer.write("NAME,AGE,MAJOR,STATUS"); // Baris header (judul kolom) pada file output
            writer.newLine();

            while ((line = reader.readLine()) != null) {
                try {
                    // Split data berdasarkan koma (delimiter / pemisah kolom CSV)
                    String[] data = line.split(",");
                    String name = data[0].toUpperCase();   // Kolom 0 = nama, diubah ke kapital
                    int age = Integer.parseInt(data[1]);   // Kolom 1 = usia; parseInt() dapat melempar NumberFormatException
                    String major = data[2];                // Kolom 2 = jurusan
                    String status = (age > 25) ? "GRADUATE" : "UNDERGRADUATE"; // Operator ternary (kondisi ? benar : salah)

                    // Tulis data yang telah diproses; String.format() = format string dengan placeholder
                    String processedLine = String.format("%s,%d,%s,%s", name, age, major, status);
                    writer.write(processedLine);
                    writer.newLine();

                    System.out.println("Processed: " + processedLine);
                } catch (NumberFormatException e) {
                    // Usia bukan angka valid -> baris dilewati agar program tidak berhenti (robust)
                    System.out.println("Baris dilewati (usia bukan angka): '" + line + "' -> " + e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Jumlah kolom kurang dari yang diharapkan -> baris dilewati
                    System.out.println("Baris dilewati (kolom tidak lengkap): '" + line + "'");
                }
            }

            System.out.println("File berhasil diproses dan disimpan sebagai " + outputFile);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    /*
     MODIFIKASI 2: Membaca file dan menghitung rata-rata usia. Baris dengan usia tidak valid diabaikan (tidak dihitung).
    */
    public static void calculateAverageAge(String filename) {
        System.out.println("\n=== RATA-RATA USIA ===");
        int totalAge = 0;   // Akumulator (penjumlah) usia
        int count = 0;      // Jumlah data usia yang valid

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    totalAge += Integer.parseInt(data[1].trim()); // Tambahkan usia ke total
                    count++;                                       // Naikkan jumlah data valid
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Multi-catch: tangani dua jenis exception dengan satu blok
                    System.out.println("Baris diabaikan pada perhitungan rata-rata: '" + line + "'");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return; // Keluar dari method bila file gagal dibaca
        }

        if (count > 0) {
            double average = (double) totalAge / count; // Cast ke double agar hasil pembagian desimal
            System.out.printf("Rata-rata usia dari %d data: %.2f%n", count, average);
        } else {
            System.out.println("Tidak ada data usia yang valid.");
        }
    }

    /*
     MODIFIKASI 3: Menambahkan satu baris data ke akhir file tanpa menghapus isi lama. FileWriter(filename, true): parameter kedua true = mode append (menambah di akhir).
    */
    
    public static void appendToFile(String filename, String newLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(newLine);
            writer.newLine();
            System.out.println("\nData ditambahkan (append) ke " + filename + ": " + newLine);
        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }
}
