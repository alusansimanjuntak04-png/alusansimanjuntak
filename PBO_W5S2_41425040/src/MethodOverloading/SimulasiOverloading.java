/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MethodOverloading;

/**
 *
 * @author user
 */

class MesinTarif {
    private String kotaAsal;

    public MesinTarif(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public double hitungOngkir(double beratKg) {
        return beratKg * 10000;
    }

    public double hitungOngkir(double beratKg, double nilaiBarang) {
        double biayaAsuransi = nilaiBarang * 0.01;
        return (beratKg * 10000) + biayaAsuransi;
    }

    public double hitungOngkir(double beratKg, boolean isExpress) {
        double total = beratKg * 10000;

        if (isExpress) {
            total += 20000;
        }

        return total;
    }
}

public class SimulasiOverloading {
    public static void main(String[] args) {
        MesinTarif tarif = new MesinTarif("Medan");

        System.out.println("=== INI METHOD OVERLOADING ===");

        System.out.println("Ongkir Biasa (2.0 Kg): Rp " 
                + tarif.hitungOngkir(2.0));

        System.out.println("Ongkir + Asuransi (Barang 5Jt): Rp " 
                + tarif.hitungOngkir(2.0, 5000000.0));

        System.out.println("Ongkir Express (2.0 Kg): Rp " 
                + tarif.hitungOngkir(2.0, true));
    }
}


/*
Lembar Analisis Overloading: 
1. Compiler bisa menentukan method yang dipakai saat compile-time
karena melihat parameter method. Yang diperiksa adalah jumlah,
tipe data, dan urutan parameter.

2. Method baru ditolak karena parameter-nya sama persis dengan
method hitungOngkir(double beratKg). Perbedaan return type int
dan double tidak bisa digunakan untuk method overloading di Java.
*/

