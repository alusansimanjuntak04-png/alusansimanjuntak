package com.praktikum.bank;

import java.util.concurrent.TimeoutException;

/**
 *
 * @author Alusan Simanjuntak (41425040)
 */
public class LatihanMandiri {
    public static void main(String[] args) throws InterruptedException {
        // ---------- Demo 1: transfer paralel dua arah ----------
        RekeningBank a = new RekeningBank(100000);
        RekeningBank b = new RekeningBank(100000);
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            final int nomor = i;
            threads[i] = new Thread(() -> {
                if (nomor % 2 == 0) {
                    a.transfer(b, 1000); // 50 thread: A -> B sebesar 1000
                } else {
                    b.transfer(a, 500);  // 50 thread: B -> A sebesar 500
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }

        // A = 100000 - 50x1000 + 50x500 = 75000; B = 125000; total tetap 200000
        System.out.println("Saldo A      : " + a.getSaldo());
        System.out.println("Saldo B      : " + b.getSaldo());
        System.out.println("Total (A+B)  : " + (a.getSaldo() + b.getSaldo()));

        // ---------- Demo 2: penarikan dengan timeout ----------
        RekeningBank kosong = new RekeningBank(0);
        try {
            kosong.tarikDenganTunggu(5000, 500); // tidak ada penyetor, menunggu maksimal 500 ms
            System.out.println("Penarikan berhasil");
        } catch (TimeoutException e) {
            System.out.println("Hasil timeout: " + e.getMessage());
        }
    }
}
