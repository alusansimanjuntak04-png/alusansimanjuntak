/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Abstraksi;

/**
 *
 * @author user
 */
abstract class KendaraanEkspedisi {
    protected String noPolisi;
    protected int kapasitasKg;

    public KendaraanEkspedisi(String noPolisi, int kapasitasKg) {
        this.noPolisi = noPolisi;
        this.kapasitasKg = kapasitasKg;
    }

    public void infoKendaraan() {
        System.out.println("Plat Nomor: " + noPolisi
                + " | Daya Angkut: " + kapasitasKg + " Kg");
    }

    public abstract void angkutBarang(String namaBarang);
}

class TrukBox extends KendaraanEkspedisi {
    public TrukBox(String noPolisi, int kapasitasKg) {
        super(noPolisi, kapasitasKg);
    }

    @Override
    public void angkutBarang(String namaBarang) {
        System.out.println("Truk [" + noPolisi + "] memuat "
                + namaBarang + " ke dalam kontainer tertutup pendingin.");
    }
}

class MobilVan extends KendaraanEkspedisi {
    public MobilVan(String noPolisi, int kapasitasKg) {
        super(noPolisi, kapasitasKg);
    }

    @Override
    public void angkutBarang(String namaBarang) {
        System.out.println("Mobil Van [" + noPolisi + "] memuat "
                + namaBarang + " di bagasi belakang untuk didistribusi kota.");
    }
}

public class SimulasiAbstraksi {
    public static void main(String[] args) {
        System.out.println("=== INI ABSTRACT CLASS ===");

        // PERCOBAAN 1: Menguji larangan instansiasi langsung abstract class
        // KendaraanEkspedisi k = new KendaraanEkspedisi("BK 1234 DEL", 100);

        // PERCOBAAN 2: Instansiasi dari subclass konkret
        TrukBox fuso = new TrukBox("B 9001 TXT", 5000);
        fuso.infoKendaraan();
        fuso.angkutBarang("20 Unit Kulkas");

        System.out.println();

        MobilVan grandmax = new MobilVan("B 1122 KAA", 800);
        grandmax.infoKendaraan();
        grandmax.angkutBarang("50 Kotak Sepatu");
    }
}

/*
Lembar Analisis Abstraksi:

Eksperimen 1:
Kalau baris ini dijalankan:

KendaraanEkspedisi k = new KendaraanEkspedisi("BK 1234 DEL", 100);

akan muncul error seperti:

"KendaraanEkspedisi is abstract; cannot be instantiated"

Alasannya karena KendaraanEkspedisi adalah abstract class.
Abstract class tidak bisa dibuat objeknya secara langsung.
Class ini hanya digunakan sebagai class dasar untuk class turunannya,
seperti TrukBox dan MobilVan.

Eksperimen 2:
Jika dibuat class MotorKurir yang extends KendaraanEkspedisi tetapi
tidak membuat method angkutBarang(), maka akan muncul error karena
method abstract tersebut belum dibuat.

Dua solusi yang biasanya ditawarkan IDE:

1. Implement methods
   -> IDE akan otomatis membuat method angkutBarang() di MotorKurir.

2. Make MotorKurir abstract
   -> MotorKurir dibuat menjadi abstract class sehingga tidak wajib
      membuat method angkutBarang().

Jadi, kalau MotorKurir mau digunakan sebagai class biasa,
solusi yang paling tepat adalah memilih "Implement methods".
*/