import java.util.*; 
import java.io.*;   

/*
 Bagian 2: Stream I/O Dasar 
 Langkah 2.1: Membaca dan Menulis File
 Nama : Alusan Simanjuntak
 NIM  : 41425040
*/

public class FileIOExample {

    // Entry point (titik masuk) program
    public static void main(String[] args) {
        String inputFile = "data.txt";     // Nama file sumber data
        String outputFile = "output.txt";  // Nama file hasil pemrosesan

        // Tulis data ke file
        writeToFile(inputFile);

        // Baca data dari file
        readFromFile(inputFile);

        // Process dan tulis ke file baru
        processFile(inputFile, outputFile);
    }

    // Menulis 4 baris data ke file (mode overwrite / menimpa isi lama)
    public static void writeToFile(String filename) {
        // try-with-resources: writer otomatis ditutup (auto-close) di akhir blok
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
        } catch (IOException e) { // Menangkap kegagalan operasi I/O
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Membaca file baris demi baris dan menampilkan dengan nomor baris
    public static void readFromFile(String filename) {
        System.out.println("\n=== MEMBACA FILE ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;          // Menampung satu baris hasil baca
            int lineNumber = 1;   // Penomoran baris, dimulai dari 1

            // readLine() mengembalikan null saat akhir file (End of File) tercapai
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }

            System.out.println("File berhasil dibaca");
        } catch (FileNotFoundException e) {   // Exception spesifik: file tidak ditemukan
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {             // Exception umum: error I/O lainnya
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Membaca inputFile, memproses tiap baris, menulis hasilnya ke outputFile
    public static void processFile(String inputFile, String outputFile) {
        System.out.println("\n=== MEMPROSES FILE ===");
        // Dua resource dalam satu try-with-resources; keduanya otomatis ditutup
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            writer.write("NAME,AGE,MAJOR,STATUS"); // Baris header pada file output
            writer.newLine();

            while ((line = reader.readLine()) != null) {
                // Split data berdasarkan koma (delimiter / pemisah kolom)
                String[] data = line.split(",");
                String name = data[0].toUpperCase();    // Kolom 0: nama -> huruf kapital
                int age = Integer.parseInt(data[1]);     // Kolom 1: usia -> konversi String ke int
                String major = data[2];                  // Kolom 2: jurusan
                String status = (age > 25) ? "GRADUATE" : "UNDERGRADUATE"; // Operator ternary

                // Tulis data yang telah diproses; String.format() = format string dengan placeholder
                String processedLine = String.format("%s,%d,%s,%s", name, age, major, status);
                writer.write(processedLine);
                writer.newLine();

                System.out.println("Processed: " + processedLine);
            }

            System.out.println("File berhasil diproses dan disimpan sebagai " + outputFile);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
