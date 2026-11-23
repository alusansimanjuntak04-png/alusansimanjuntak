// Exception buatan sendiri, dipakai kalau umur belum cukup buat bikin KTP
class UmurTidakCukupException extends Exception {

    public UmurTidakCukupException(String pesan) {
        super(pesan); // kirim pesan error ke class induk
    }
}

// Class KTP: nyimpan data KTP dan ngecek datanya
class KTP {

    private String nik;
    private String nama;
    private int umur;

    // throws = exception-nya nggak ditangani di sini, tapi dilempar ke yang manggil
    public KTP(String nik, String nama, int umur) throws UmurTidakCukupException {

        // Cek NIK harus 16 digit angka
        if (!nik.matches("\\d{16}")) {
            // throw = lempar exception (ini exception bawaan Java)
            throw new IllegalArgumentException("NIK harus 16 digit angka");
        }

        // Cek umur minimal 17 tahun
        if (umur < 17) {
            // lempar exception buatan sendiri
            throw new UmurTidakCukupException(
                    nama + " baru berumur " + umur + " tahun (minimal 17 tahun)");
        }

        // Kalau lolos semua, simpan datanya
        this.nik = nik;
        this.nama = nama;
        this.umur = umur;
    }

    // Tampilkan isi KTP
    public void tampilkan() {
        System.out.println("NIK  : " + nik);
        System.out.println("Nama : " + nama);
        System.out.println("Umur : " + umur + " tahun");
    }
}

// Class utama buat jalanin program
public class Main {

    // Proses satu pengajuan KTP
    public static void buatKTP(String nik, String nama, int umur) {
        System.out.println("\n--- Pengajuan KTP: " + nama + " ---");

        // penanda: false = gagal, true = berhasil (awalnya dianggap gagal)
        boolean berhasil = false;

        // try = kode yang mungkin error ditaruh di sini
        try {
            KTP ktp = new KTP(nik, nama, umur);
            System.out.println("KTP berhasil dibuat:");
            ktp.tampilkan();
            berhasil = true; // sampai sini berarti tidak ada error

        // catch = tangkap error umur (exception buatan sendiri)
        } catch (UmurTidakCukupException e) {
            System.out.println("Ditolak: " + e.getMessage());

        // catch = tangkap error NIK (exception bawaan Java)
        } catch (IllegalArgumentException e) {
            System.out.println("Ditolak: " + e.getMessage());

        // finally = selalu jalan, mau berhasil atau gagal
        } finally {
            // pesannya dibedakan lewat penanda "berhasil"
            if (berhasil) {
                System.out.println("Proses pengajuan selesai: BERHASIL.");
            } else {
                System.out.println("Proses pengajuan selesai: GAGAL.");
            }
        }
    }

    public static void main(String[] args) {
        buatKTP("1206121508000001", "Soritua Manurung", 40); // berhasil
        buatKTP("1206121005120003", "Citra Akkora Saritua Sitorus", 16); // umur kurang
        buatKTP("12061215", "Alusan Pargomgoman Parulian Simandjuntak", 25);       // NIK salah
    }
}