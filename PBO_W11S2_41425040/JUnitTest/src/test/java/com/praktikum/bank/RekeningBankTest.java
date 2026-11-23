package com.praktikum.bank;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author Alusan
 */
public class RekeningBankTest {

    private RekeningBank rekening;

    // Dijalankan sebelum SETIAP test: rekening baru bersaldo 0 (tiap test independen)
    @BeforeEach
    void setUp() {
        rekening = new RekeningBank(0);
    }

    @Test
    @DisplayName("Skenario 1: Transaksi setor dan tarik normal secara sekuensial")
    void testTransaksiNormal() throws InterruptedException {
        rekening.setor(50000);
        rekening.tarikDenganTunggu(20000);
        // 50000 - 20000 = 30000
        assertEquals(30000, rekening.getSaldo(), "Saldo akhir harus tepat 30000");
    }

    @Test
    @DisplayName("Skenario 2: Input nominal negatif atau nol harus melempar Exception")
    void testInputTidakValid() {
        // Test lulus jika setor(-5000) melempar IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            rekening.setor(-5000);
        }, "Setor negatif harus ditolak dengan IllegalArgumentException");
    }

    @Test
    @DisplayName("Skenario 3: Bebas Race Condition pada eksekusi 100 Thread bersamaan")
    void testConcurrencySetor() throws InterruptedException {
        int jumlahThread = 100;
        int nominalSetor = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(20);   // thread pool 20 thread
        CountDownLatch latch = new CountDownLatch(jumlahThread);       // penghitung mundur 100 tugas

        for (int i = 0; i < jumlahThread; i++) {
            executor.submit(() -> {
                try {
                    rekening.setor(nominalSetor);
                } finally {
                    latch.countDown();  // kurangi hitungan walau terjadi error
                }
            });
        }

        latch.await();      // tunggu sampai hitungan 0 (semua setoran selesai)
        executor.shutdown();

        assertEquals(100000, rekening.getSaldo(),
            "Saldo harus tepat 100000 tanpa kehilangan data akibat race condition");
    }

    @Test
    @DisplayName("Skenario 4: Koordinasi Thread menggunakan wait() dan notifyAll()")
    void testWaitNotifyTarikTunggu() {
        // Batas 2 detik: jika thread macet (deadlock/menunggu selamanya), test gagal
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            Thread threadPenarik = new Thread(() -> {
                try {
                    rekening.tarikDenganTunggu(50000); // saldo 0, jadi thread ini wait()
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread threadPenyetor = new Thread(() -> {
                try {
                    Thread.sleep(200);          // sengaja terlambat 200 ms
                    rekening.setor(70000);      // setor lalu notifyAll() membangunkan penarik
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            threadPenarik.start();
            threadPenyetor.start();

            threadPenarik.join();
            threadPenyetor.join();

            // 70000 - 50000 = 20000
            assertEquals(20000, rekening.getSaldo(),
                "Thread penarik harus menunggu penyetor, lalu saldo tersisa 20000");
        });
    }
}
