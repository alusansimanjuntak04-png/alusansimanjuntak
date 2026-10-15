// Class untuk menyimpan data yang dipakai bersama
class AntreanShared {

    private String data;
    private boolean adaData = false;

    // Method untuk mengirim data
    public synchronized void kirimPesan(String pesan) {
        while (adaData) {
            try {
                // Menunggu sampai data sebelumnya diambil
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        this.data = pesan;
        this.adaData = true;

        System.out.println("Pesan dikirim: " + pesan);

        // Memberitahu thread konsumen bahwa data siap
        notify();
    }

    // Method untuk mengambil data
    public synchronized String ambilPesan() {
        while (!adaData) {
            try {
                // Menunggu sampai ada data
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        this.adaData = false;

        System.out.println("Pesan diterima: " + data);

        // Memberitahu thread produsen bahwa tempat sudah kosong
        notify();

        return data;
    }
}

// Class utama
public class MainConcurrency {

    public static void main(String[] args) {

        // Membuat antrean yang dipakai bersama
        AntreanShared antrean = new AntreanShared();

        // Thread Produsen
        Thread produsen = new Thread(() -> {

            // Daftar pesan yang akan dikirim
            String[] listPesan = {"Halo", "Belajar Java", "Selesai"};

            // Mengirim semua pesan
            for (String p : listPesan) {
                antrean.kirimPesan(p);
            }
        });

        // Thread Konsumen
        Thread konsumen = new Thread(() -> {

            // Mengambil 3 pesan
            for (int i = 0; i < 3; i++) {
                antrean.ambilPesan();
            }
        });

        // Menjalankan thread produsen
        produsen.start();

        // Menjalankan thread konsumen
        konsumen.start();
    }
}