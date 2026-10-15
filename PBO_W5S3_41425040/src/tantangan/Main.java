/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tantangan;

/**
 *
 * @author user
 */
import java.util.*;

import java.util.ArrayList;
import java.util.List;

// 1. Abstract class MantraPerlindungan
abstract class MantraPerlindungan {
    public abstract void aktifkan();
    public abstract void nonaktifkan();
}

// 2. Kelas Protego
class Protego extends MantraPerlindungan {

    @Override
    public void aktifkan() {
        System.out.println("Perisai Protego terbentuk!");
    }

    @Override
    public void nonaktifkan() {
        System.out.println("Perisai Protego dinonaktifkan.");
    }
}

// 3. Kelas Fidelius
class Fidelius extends MantraPerlindungan {
    private String secretKeeper;

    public Fidelius(String secretKeeper) {
        this.secretKeeper = secretKeeper;
    }

    @Override
    public void aktifkan() {
        System.out.println(
            "Mantra Fidelius diaktifkan! Secret Keeper: "
            + secretKeeper
        );
    }

    @Override
    public void nonaktifkan() {
        System.out.println("Mantra Fidelius dinonaktifkan.");
    }
}

// 4. Kelas MantraAntiApparition
class MantraAntiApparition extends MantraPerlindungan {
    private String area;

    public MantraAntiApparition(String area) {
        this.area = area;
    }

    @Override
    public void aktifkan() {
        System.out.println(
            "Apparition dicegah di area " + area + "!"
        );
    }

    @Override
    public void nonaktifkan() {
        System.out.println(
            "Perlindungan Anti-Apparition di area "
            + area + " dinonaktifkan."
        );
    }
}

// 5. Kelas SistemPertahanan
class SistemPertahanan {
    private List<MantraPerlindungan> daftarMantra =
        new ArrayList<>();

    // Menambahkan mantra ke sistem
    public void tambahMantra(MantraPerlindungan mantra) {
        daftarMantra.add(mantra);
        System.out.println("Mantra berhasil ditambahkan.");
    }

    // Mengaktifkan semua mantra
    public void aktifkanSemuaMantra() {
        System.out.println(
            "\n=== Mengaktifkan Semua Mantra ==="
        );

        for (MantraPerlindungan mantra : daftarMantra) {
            mantra.aktifkan();
        }
    }

    // Menonaktifkan semua mantra
    public void nonaktifkanSemuaMantra() {
        System.out.println(
            "\n=== Menonaktifkan Semua Mantra ==="
        );

        for (MantraPerlindungan mantra : daftarMantra) {
            mantra.nonaktifkan();
        }
    }
}

// 6. Program utama
public class Main {
    public static void main(String[] args) {

        // Membuat sistem pertahanan Hogwarts
        SistemPertahanan hogwarts = new SistemPertahanan();

        // Membuat objek mantra
        MantraPerlindungan protego = new Protego();

        MantraPerlindungan fidelius =
            new Fidelius("Albus Dumbledore");

        MantraPerlindungan antiApparition =
            new MantraAntiApparition("Hogwarts");

        // Menambahkan mantra ke sistem
        hogwarts.tambahMantra(protego);
        hogwarts.tambahMantra(fidelius);
        hogwarts.tambahMantra(antiApparition);

        // Mengaktifkan semua mantra
        hogwarts.aktifkanSemuaMantra();

        // Menonaktifkan semua mantra
        hogwarts.nonaktifkanSemuaMantra();
    }
}
