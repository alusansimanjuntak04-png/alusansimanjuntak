/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MethodOverrding;

/**
 *
 * @author user
 */
class KurirReguler {
    protected String nama;

    public KurirReguler(String nama) {
        this.nama = nama;
    }

    public void kirimBarang(String alamat) {
        System.out.println(nama + " mengantar paket ke " + alamat
                + " lewat jalur darat menembus kemacetan.");
    }
}

class KurirDrone extends KurirReguler {
    private String idDrone;

    public KurirDrone(String nama, String idDrone) {
        super(nama);
        this.idDrone = idDrone;
    }

    @Override
    public void kirimBarang(String alamat) {
        System.out.println("Unit Drone [" + idDrone + "] operator " + nama
                + " terbang mengantar paket langsung ke koordinat: " + alamat);
    }
}

public class SimulasiOverriding {
    public static void main(String[] args) {
        System.out.println("=== INI METHOD OVERRIDING ===");

        KurirReguler k1 = new KurirReguler("Budi");

        KurirReguler k2 = new KurirDrone("Arka", "DRN-01");

        k1.kirimBarang("Jl. Sudirman No. 10");
        k2.kirimBarang("Gedung Graha Lt. 5");
    }
}

/*
Analisis Overriding:

1. Setelah @Override dihapus dan nama method dibuat salah ketik
   menjadi kirimbarang(), method tersebut tidak dianggap sebagai
   overriding.

   Jadi saat k2.kirimBarang("Gedung Graha Lt. 5") dipanggil,
   yang jalan adalah method milik KurirReguler.

   Output-nya menjadi:
   Arka mengantar paket ke Gedung Graha Lt. 5 lewat jalur darat
   menembus kemacetan.

2. Kalau @Override dikembalikan, compiler akan menemukan kesalahan
   karena method kirimbarang() tidak sama dengan method yang ada
   di class induk.

   Pesan error yang biasanya muncul:
   "method does not override or implement a method from a supertype"

3. Fungsi @Override adalah untuk memberi tahu compiler bahwa method
   tersebut harus benar-benar menimpa method dari class induknya.
   Jadi kalau nama atau parameternya salah, compiler langsung memberi
   tahu programmer. Dengan begitu kesalahan lebih cepat ditemukan.
*/