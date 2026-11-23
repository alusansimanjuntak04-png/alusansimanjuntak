import java.io.*;   // Import paket I/O (BufferedReader, BufferedWriter, FileReader, FileWriter, IOException)
import java.util.*; // Import paket utilitas (Map, HashMap, LinkedHashMap, List, ArrayList, Collections)

/*
  Modul PBO W9S2 - Bagian 3: Integrasi & Studi Kasus (KODE MODIFIKASI)
  Nama : Alusan Simanjuntak
  NIM  : 41425040
 
  
  P1. Bagaimana cara kerja HashMap untuk menghitung frekuensi karakter?
  J1. HashMap<Character, Integer> menyimpan pasangan key-value: key = karakter,
      value = jumlah kemunculan. Untuk setiap huruf, program memanggil
      charFrequency.getOrDefault(c, 0) + 1 (ambil hitungan lama, atau 0 bila key
      belum ada, lalu tambah 1) dan put() kembali. Secara internal HashMap memakai
      hashCode() key untuk menentukan bucket, sehingga get/put rata-rata O(1).
      HashMap tidak berurutan, sehingga hasilnya disalin ke List<Map.Entry> lalu
      diurutkan berdasarkan nilai (menurun).
 
  P2. Mengapa kita menggunakan \\s+ untuk split words daripada spasi biasa?
  J2. "\\s+" adalah regular expression: \s = karakter whitespace apa pun (spasi, tab,
      newline) dan + = satu atau lebih. Pada split(" "), dua spasi berturut-turut
      menghasilkan elemen string kosong sehingga jumlah kata keliru, dan tab tidak
      ikut terpisah. "\\s+" menganggap kumpulan whitespace sebagai satu pemisah.
      Tanda "\\" diperlukan karena karakter \ harus di-escape dalam literal String Java.
      (Demo: lihat demoSplit() yang dipanggil dari main.)
 
  P3. Apa keuntungan menggunakan BufferedReader/BufferedWriter?
  J3. Keduanya memakai BUFFER di memori (default 8192 karakter), sehingga jumlah akses
      langsung ke disk/OS berkurang dan performa jauh lebih baik dibanding
      FileReader/FileWriter murni yang membaca/menulis per karakter. Selain itu
      menyediakan method praktis: readLine() (baca per baris) dan newLine()
      (pemisah baris sesuai OS).
 
 TUGAS MODIFIKASI 
  1. MUDAH : menghitung jumlah kalimat (berdasarkan tanda titik)
        -> variabel totalSentences di processTextFile()
  2. SEDANG: menemukan kata yang paling sering muncul
        -> wordFrequency di processTextFile()
  3. SULIT : memproses file CSV dan menghasilkan summary statistics
        -> createSampleCsv(), analyzeCsv(), summarizeColumn()
 */

public class TextProcessorModifikasi {

    // Entry point (titik masuk) program
    public static void main(String[] args) {
        String sourceFile = "source.txt";       // File sumber teks
        String processedFile = "processed.txt"; // File hasil konversi huruf kapital
        String reportFile = "report.txt";       // File laporan statistik

        // Buat file sumber contoh
        createSampleFile(sourceFile);

        // Process file: uppercase + statistics (+ jumlah kalimat & kata paling sering)
        processTextFile(sourceFile, processedFile, reportFile);

        // Tampilkan report
        displayReport(reportFile);

        // DEMO P2: perbedaan split(" ") dan split("\\s+")
        demoSplit();

        // TUGAS SULIT: proses file CSV dan hasilkan summary statistics
        String csvFile = "students.csv";
        String summaryFile = "summary.txt";
        createSampleCsv(csvFile);
        analyzeCsv(csvFile, summaryFile);
        displayFile(summaryFile, "CSV SUMMARY");
    }

    /*
      Membuat file sumber berisi 5 baris teks contoh.
    */
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

    /*
      Membaca inputFile, menulis versi kapital ke outputFile, lalu menulis statistik
      (baris, kata, karakter, kalimat, kata terbanyak, frekuensi karakter) ke reportFile.
      BufferedReader/BufferedWriter dipakai agar I/O efisien (lihat J3).
    */
    public static void processTextFile(String inputFile, String outputFile, String reportFile) {
        // Tiga resource dibuka sekaligus; semuanya otomatis ditutup (auto-close)
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
             BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFile))) {

            String line;
            int totalLines = 0;     // Penghitung jumlah baris
            int totalWords = 0;     // Penghitung jumlah kata
            int totalChars = 0;     // Penghitung jumlah karakter
            int totalSentences = 0; // [MODIFIKASI MUDAH] penghitung jumlah kalimat
            Map<Character, Integer> charFrequency = new HashMap<>(); // Peta karakter -> frekuensi (lihat J1)
            Map<String, Integer> wordFrequency = new HashMap<>();    // [MODIFIKASI SEDANG] peta kata -> frekuensi

            while ((line = reader.readLine()) != null) {
                totalLines++;

                // Process line: convert to uppercase
                String processedLine = line.toUpperCase();
                writer.write(processedLine);
                writer.newLine();

                // Calculate statistics
                // "\\s+" = regular expression: satu atau lebih whitespace sebagai pemisah kata (lihat J2)
                String[] words = line.split("\\s+");
                totalWords += words.length;
                totalChars += line.length();

                // [MODIFIKASI MUDAH] Hitung kalimat: setiap karakter titik '.' dianggap pengakhir kalimat
                for (char c : line.toCharArray()) {
                    if (c == '.') {
                        totalSentences++;
                    }
                }

                // Character frequency
                for (char c : line.toLowerCase().toCharArray()) {
                    if (Character.isLetter(c)) { // Hanya hitung huruf (abaikan spasi dan tanda baca)
                        // getOrDefault(c, 0): ambil hitungan lama (atau 0 jika belum ada), lalu +1
                        charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
                    }
                }

                // [MODIFIKASI SEDANG] Word frequency
                for (String word : words) {
                    // Normalisasi: huruf kecil + hapus semua karakter non-huruf (mis. titik di akhir kata)
                    String cleaned = word.toLowerCase().replaceAll("[^a-z]", "");
                    if (!cleaned.isEmpty()) { // Lewati token kosong
                        wordFrequency.put(cleaned, wordFrequency.getOrDefault(cleaned, 0) + 1);
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
            reportWriter.write("Total Sentences: " + totalSentences); // [MODIFIKASI MUDAH]
            reportWriter.newLine();
            // (double) = type casting agar pembagian menghasilkan desimal, bukan integer division
            reportWriter.write("Average Words per Line: " + (double) totalWords / totalLines);
            reportWriter.newLine();
            reportWriter.newLine();

            // [MODIFIKASI SEDANG] Cari kata yang paling sering muncul
            reportWriter.write("MOST FREQUENT WORD(S):");
            reportWriter.newLine();
            int maxCount = Collections.max(wordFrequency.values()); // Frekuensi tertinggi
            List<String> topWords = new ArrayList<>();
            for (Map.Entry<String, Integer> e : wordFrequency.entrySet()) {
                if (e.getValue() == maxCount) {  // Kumpulkan semua kata berfrekuensi tertinggi (menangani seri / tie)
                    topWords.add(e.getKey());
                }
            }
            Collections.sort(topWords); // Urutkan alfabetis agar output deterministik
            for (String w : topWords) {
                reportWriter.write(w + ": " + maxCount);
                reportWriter.newLine();
            }
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

    /*
      Membaca reportFile lalu menampilkannya ke console.
    */
    
    public static void displayReport(String reportFile) {
        System.out.println("\n=== PROCESSING REPORT ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Cetak setiap baris laporan
            }
        } catch (IOException e) {
            System.out.println("Error reading report: " + e.getMessage());
        }
    }

    /*
      DEMO P2: membandingkan split(" ") dengan split("\\s+") pada teks yang memiliki spasi ganda dan tab.
    */
    public static void demoSplit() {
        System.out.println("\n=== DEMO P2: SPLIT(\" \") vs SPLIT(\"\\\\s+\") ===");
        String text = "Java  programming\tis   fun"; // Ada 2 spasi, 1 tab, dan 3 spasi sebagai pemisah
        String[] bySpace = text.split(" ");          // Pemisah satu spasi persis
        String[] byRegex = text.split("\\s+");       // Pemisah satu atau lebih whitespace
        System.out.println("split(\" \")     -> jumlah elemen: " + bySpace.length + " " + Arrays.toString(bySpace)); // Ada elemen kosong & tab tidak terpisah
        System.out.println("split(\"\\\\s+\") -> jumlah elemen: " + byRegex.length + " " + Arrays.toString(byRegex)); // 4 kata bersih
    }

    
    //  TUGAS MODIFIKASI SULIT: pemrosesan file CSV + summary statistics
    /*
      Membuat file CSV contoh. Sengaja ada satu nilai kosong (missing value) pada kolom Score
      untuk menguji ketahanan (robustness) program.
    */
    public static void createSampleCsv(String filename) {
        String[] rows = {
            "Name,Age,Score,Major",
            "Alice,20,85.5,Informatika",
            "Bob,21,78,Sistem Informasi",
            "Charlie,19,92.25,Informatika",
            "Diana,22,66,Teknik Elektro",
            "Eka,20,,Informatika",          // Nilai Score kosong (missing value)
            "Fajar,23,88,Sistem Informasi"
        };
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String row : rows) {
                writer.write(row);
                writer.newLine();
            }
            System.out.println("\nSample CSV created: " + filename);
        } catch (IOException e) {
            System.out.println("Error creating sample CSV: " + e.getMessage());
        }
    }

    /*
      Membaca CSV, menghitung statistik per kolom, dan menulis hasilnya ke summaryFile.
      Kolom numerik : count, missing, sum, mean, min, max, median, standard deviation.
      Kolom teks    : count, missing, jumlah nilai unik, nilai paling sering (mode).
    */
    
    public static void analyzeCsv(String csvFile, String summaryFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(summaryFile))) {

            // Baris pertama = header (nama-nama kolom)
            String headerLine = reader.readLine();
            if (headerLine == null) { // File kosong -> tidak ada yang dianalisis
                System.out.println("File CSV kosong.");
                return;
            }
            String[] headers = headerLine.split(",");
            int numCols = headers.length;

            // columns.get(i) = daftar semua nilai (String) pada kolom ke-i
            List<List<String>> columns = new ArrayList<>();
            for (int i = 0; i < numCols; i++) {
                columns.add(new ArrayList<>());
            }

            String line;
            int rowCount = 0;      // Jumlah baris data valid
            int skippedRows = 0;   // Jumlah baris yang dilewati karena jumlah kolom salah
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Abaikan baris kosong
                // split(",", -1): limit negatif agar kolom kosong di akhir baris tetap terbaca
                String[] values = line.split(",", -1);
                if (values.length != numCols) {      // Validasi jumlah kolom
                    skippedRows++;
                    System.out.println("Baris dilewati (jumlah kolom tidak sesuai): '" + line + "'");
                    continue;
                }
                for (int i = 0; i < numCols; i++) {
                    columns.get(i).add(values[i].trim()); // Simpan nilai ke kolomnya
                }
                rowCount++;
            }

            // Tulis ringkasan
            writeLine(writer, "=== CSV SUMMARY STATISTICS ===");
            writeLine(writer, "File          : " + csvFile);
            writeLine(writer, "Total Rows    : " + rowCount);
            writeLine(writer, "Skipped Rows  : " + skippedRows);
            writeLine(writer, "Total Columns : " + numCols);

            for (int i = 0; i < numCols; i++) {
                writeLine(writer, "");
                writeLine(writer, "--- Column: " + headers[i].trim() + " ---");
                summarizeColumn(writer, columns.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error processing CSV: " + e.getMessage());
        }
    }

    /*
      Meringkas satu kolom. Jika semua nilai tidak kosong dapat diparse menjadi double,
      kolom diperlakukan numerik; selain itu diperlakukan sebagai teks.
    */
    
    private static void summarizeColumn(BufferedWriter writer, List<String> values) throws IOException {
        List<Double> numbers = new ArrayList<>(); // Menampung nilai numerik valid
        int missing = 0;                          // Jumlah nilai kosong
        boolean numeric = true;                   // Asumsi awal: kolom numerik

        for (String v : values) {
            if (v.isEmpty()) {   // Nilai kosong dihitung sebagai missing, bukan error
                missing++;
                continue;
            }
            try {
                numbers.add(Double.parseDouble(v)); // Coba konversi ke angka
            } catch (NumberFormatException e) {
                numeric = false;                    // Ada nilai non-angka -> kolom bertipe teks
                break;
            }
        }

        if (numeric && !numbers.isEmpty()) {
            // ----- Statistik kolom numerik -----
            int n = numbers.size();
            double sum = 0;
            for (double x : numbers) sum += x;          // Jumlah seluruh nilai
            double mean = sum / n;                      // Rata-rata (mean) = jumlah / banyak data

            List<Double> sorted = new ArrayList<>(numbers);
            Collections.sort(sorted);                   // Urutkan menaik untuk min, max, dan median
            double min = sorted.get(0);
            double max = sorted.get(n - 1);
            // Median: nilai tengah; jika n genap, rata-rata dua nilai tengah
            double median = (n % 2 == 1)
                    ? sorted.get(n / 2)
                    : (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;

            // Standar deviasi sampel: sqrt( sum((x - mean)^2) / (n - 1) ); untuk n = 1 bernilai 0
            double sqDiff = 0;
            for (double x : numbers) sqDiff += (x - mean) * (x - mean);
            double stdDev = (n > 1) ? Math.sqrt(sqDiff / (n - 1)) : 0.0;

            writeLine(writer, "Type    : numeric");
            writeLine(writer, "Count   : " + n);
            writeLine(writer, "Missing : " + missing);
            writeLine(writer, String.format("Sum     : %.2f", sum));
            writeLine(writer, String.format("Mean    : %.2f", mean));
            writeLine(writer, String.format("Min     : %.2f", min));
            writeLine(writer, String.format("Max     : %.2f", max));
            writeLine(writer, String.format("Median  : %.2f", median));
            writeLine(writer, String.format("Std Dev : %.2f", stdDev));
        } else {
            // ----- Statistik kolom teks (kategorikal) -----
            Map<String, Integer> freq = new LinkedHashMap<>(); // LinkedHashMap menjaga urutan kemunculan
            int nonEmpty = 0;
            for (String v : values) {
                if (v.isEmpty()) continue;
                freq.put(v, freq.getOrDefault(v, 0) + 1); // Hitung frekuensi tiap nilai
                nonEmpty++;
            }
            String modeValue = "-";   // Nilai yang paling sering muncul (mode)
            int modeCount = 0;
            for (Map.Entry<String, Integer> e : freq.entrySet()) {
                if (e.getValue() > modeCount) { // '>' (bukan '>=') => saat seri, yang muncul lebih dulu dipilih
                    modeValue = e.getKey();
                    modeCount = e.getValue();
                }
            }
            writeLine(writer, "Type    : text");
            writeLine(writer, "Count   : " + nonEmpty);
            writeLine(writer, "Missing : " + (values.size() - nonEmpty));
            writeLine(writer, "Unique  : " + freq.size());
            writeLine(writer, "Mode    : " + modeValue + " (" + modeCount + "x)");
        }
    }

    // Helper: menulis satu baris ke writer (teks + newline)
    private static void writeLine(BufferedWriter writer, String text) throws IOException {
        writer.write(text);
        writer.newLine();
    }

    // Helper: menampilkan isi file ke console dengan judul
    public static void displayFile(String filename, String title) {
        System.out.println("\n=== " + title + " ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
