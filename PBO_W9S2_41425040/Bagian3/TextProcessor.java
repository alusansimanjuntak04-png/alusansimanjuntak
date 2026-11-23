import java.io.*;   
import java.util.*; 

/*
 Bagian 3: Integrasi & Studi Kasus 
 Langkah 3.1: Program Text Processing yang Komprehensif
 Nama : Alusan Simanjuntak
 NIM  : 41425040
*/

public class TextProcessor {

    // Entry point (titik masuk) program
    public static void main(String[] args) {
        String sourceFile = "source.txt";       // File sumber teks
        String processedFile = "processed.txt"; // File hasil konversi huruf kapital
        String reportFile = "report.txt";       // File laporan statistik

        // Buat file sumber contoh
        createSampleFile(sourceFile);

        // Process file: uppercase + statistics
        processTextFile(sourceFile, processedFile, reportFile);

        // Tampilkan report
        displayReport(reportFile);
    }

    // Membuat file sumber berisi 5 baris teks contoh
    public static void createSampleFile(String filename) {
        String[] sampleText = {   // Array String berisi baris-baris teks contoh
            "Java programming is fun and powerful.",
            "Streams and Files are important concepts.",
            "Exception handling makes code robust.",
            "String manipulation is frequently used.",
            "Practice makes perfect in programming."
        };

        // try-with-resources: writer otomatis ditutup setelah blok selesai
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : sampleText) { // Iterasi setiap baris contoh
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Sample file created: " + filename);
        } catch (IOException e) {
            System.out.println("Error creating sample file: " + e.getMessage());
        }
    }

    // Membaca inputFile, menulis versi kapital ke outputFile, dan menulis statistik ke reportFile
    public static void processTextFile(String inputFile, String outputFile, String reportFile) {
        // Tiga resource dibuka sekaligus; semuanya otomatis ditutup (auto-close)
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
             BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFile))) {

            String line;
            int totalLines = 0;   // Penghitung jumlah baris
            int totalWords = 0;   // Penghitung jumlah kata
            int totalChars = 0;   // Penghitung jumlah karakter
            Map<Character, Integer> charFrequency = new HashMap<>(); // Peta karakter -> frekuensi

            while ((line = reader.readLine()) != null) {
                totalLines++;

                // Process line: convert to uppercase
                String processedLine = line.toUpperCase();
                writer.write(processedLine);
                writer.newLine();

                // Calculate statistics
                String[] words = line.split("\\s+"); // Regex: satu atau lebih whitespace sebagai pemisah kata
                totalWords += words.length;
                totalChars += line.length();

                // Character frequency
                for (char c : line.toLowerCase().toCharArray()) {
                    if (Character.isLetter(c)) { // Hanya hitung huruf
                        // getOrDefault(c, 0): ambil hitungan lama (atau 0), lalu tambah 1
                        charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
                    }
                }
            }

            // Write report
            reportWriter.write("=== TEXT PROCESSING REPORT ===");
            reportWriter.newLine();
            reportWriter.write("Total Lines: " + totalLines);
            reportWriter.newLine();
            reportWriter.write("Total Words: " + totalWords);
            reportWriter.newLine();
            reportWriter.write("Total Characters: " + totalChars);
            reportWriter.newLine();
            // (double) = type casting agar pembagian menghasilkan desimal
            reportWriter.write("Average Words per Line: " + (double) totalWords / totalLines);
            reportWriter.newLine();
            reportWriter.newLine();
            reportWriter.write("CHARACTER FREQUENCY:");
            reportWriter.newLine();

            // Sort characters by frequency
            List<Map.Entry<Character, Integer>> sortedChars = new ArrayList<>(charFrequency.entrySet());
            // Lambda comparator: urut menurun (descending) berdasarkan frekuensi
            sortedChars.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            for (Map.Entry<Character, Integer> entry : sortedChars) {
                reportWriter.write(entry.getKey() + ": " + entry.getValue());
                reportWriter.newLine();
            }

            System.out.println("File processing completed!");
            System.out.println("  - Input: " + inputFile);
            System.out.println("  - Output: " + outputFile);
            System.out.println("  - Report: " + reportFile);

        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }

    // Membaca reportFile lalu menampilkannya ke console
    public static void displayReport(String reportFile) {
        System.out.println("\n=== PROCESSING REPORT ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading report: " + e.getMessage());
        }
    }
}
