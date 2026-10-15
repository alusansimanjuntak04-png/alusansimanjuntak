/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bagian_B_LatihanMandiri;

/**
 *
 * @author user
 */


class StudentGrade {

    // Atribut private
    private String nim;
    private String nama;
    private double nilaiKuis;
    private double nilaiUTS;
    private String gradeHuruf;

    // Constructor
    public StudentGrade(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.nilaiKuis = 0.0;
        this.nilaiUTS = 0.0;

        updateGradeHuruf();

        System.out.println("[Registrasi] Berhasil mendaftarkan mahasiswa: "
                + nim + " - " + nama);
    }

    // Getter NIM (read-only)
    public String getNim() {
        return nim;
    }

    // Getter Nama (read-only)
    public String getNama() {
        return nama;
    }

    // Getter nilai kuis
    public double getNilaiKuis() {
        return nilaiKuis;
    }

    // Setter nilai kuis dengan validasi
    public void setNilaiKuis(double nilaiKuis) {

        if (nilaiKuis < 0.0 || nilaiKuis > 100.0) {
            System.out.println("[Error] Nilai kuis tidak valid: "
                    + nilaiKuis + " (Harus antara 0 - 100)");
            return;
        }

        this.nilaiKuis = nilaiKuis;

        // Update grade hanya jika nilai berhasil diubah
        updateGradeHuruf();

        System.out.println("[Update] Nilai kuis berhasil diubah menjadi: "
                + nilaiKuis);
    }

    // Getter nilai UTS
    public double getNilaiUTS() {
        return nilaiUTS;
    }

    // Setter nilai UTS dengan validasi
    public void setNilaiUTS(double nilaiUTS) {

        if (nilaiUTS < 0.0 || nilaiUTS > 100.0) {
            System.out.println("[Error] Nilai UTS tidak valid: "
                    + nilaiUTS + " (Harus antara 0 - 100)");
            return;
        }

        this.nilaiUTS = nilaiUTS;

        // Update grade hanya jika nilai berhasil diubah
        updateGradeHuruf();

        System.out.println("[Update] Nilai UTS berhasil diubah menjadi: "
                + nilaiUTS);
    }

    // Getter gradeHuruf (read-only)
    public String getGradeHuruf() {
        return gradeHuruf;
    }

    // Menghitung nilai akhir
    public double hitungNilaiAkhir() {
        return (nilaiKuis * 0.40) + (nilaiUTS * 0.60);
    }

    // Method private untuk menghitung grade secara otomatis
    private void updateGradeHuruf() {

        double nilaiAkhir = hitungNilaiAkhir();

        if (nilaiAkhir >= 80) {
            gradeHuruf = "A";
        } else if (nilaiAkhir >= 70) {
            gradeHuruf = "B";
        } else if (nilaiAkhir >= 60) {
            gradeHuruf = "C";
        } else if (nilaiAkhir >= 50) {
            gradeHuruf = "D";
        } else {
            gradeHuruf = "E";
        }
    }

    // Mencetak laporan hasil studi
    public void cetakLaporan() {

        System.out.println();
        System.out.println("=== KARTU HASIL STUDI ===");
        System.out.println("NIM         : " + nim);
        System.out.println("Nama        : " + nama);
        System.out.println("Nilai Kuis  : " + nilaiKuis);
        System.out.println("Nilai UTS   : " + nilaiUTS);
        System.out.println("Nilai Akhir : " + hitungNilaiAkhir());
        System.out.println("Grade Huruf : " + gradeHuruf);
    }
}


// CLASS UTAMA
public class NilaiMahasiswa {

    public static void main(String[] args) {

        // Membuat objek mahasiswa
        StudentGrade mahasiswa =
                new StudentGrade("11425001", "Marsha");

        // Nilai kuis tidak valid
        mahasiswa.setNilaiKuis(110.0);

        // Nilai kuis valid
        mahasiswa.setNilaiKuis(85.0);

        // Nilai UTS valid
        mahasiswa.setNilaiUTS(90.0);

        // Cetak laporan
        mahasiswa.cetakLaporan();
    }
}