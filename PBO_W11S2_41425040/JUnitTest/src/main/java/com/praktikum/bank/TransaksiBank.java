package com.praktikum.bank;

/**
 *
 * @author alusan
 */
public class TransaksiBank {
    public static void main(String[] args) throws InterruptedException {
        RekeningBank rekening = new RekeningBank(0);   // saldo awal 0
        Thread[] threads = new Thread[100];            // array 100 thread

        // Buat dan jalankan 100 thread, tiap thread menyetor 1000 (lambda = Runnable)
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> rekening.setor(1000));
            threads[i].start();
        }

        // join(): thread main menunggu semua thread selesai sebelum mencetak hasil
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Saldo Seharusnya : 100000");               // 100 x 1000
        System.out.println("Saldo Aktual     : " + rekening.getSaldo()); // < 100000 akibat race condition
    }
}
