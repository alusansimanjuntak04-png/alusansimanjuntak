/*package com.praktikum.bank;*/

/*
 *
 * @author Alusan
 */

/*
public class RekeningBank {
    // Resource bersama (shared resource: data yang diakses banyak thread sekaligus)
    private int saldo;

    public RekeningBank(int saldoAwal) {
        this.saldo = saldoAwal;
    }

    // Tidak ada kata kunci synchronized, jadi banyak thread bisa masuk bersamaan
    public void setor(int jumlah) {
        int saldoSementara = this.saldo;        // 1) BACA saldo lama
        try {
            Thread.sleep(1);                    // 2) jeda 1 ms, thread lain sempat membaca saldo lama yang sama
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // pulihkan status interrupt (tanda thread diminta berhenti)
        }
        this.saldo = saldoSementara + jumlah;   // 3) TULIS saldo baru, menimpa hasil thread lain (lost update)
    }

    public void tarik(int jumlah) {
        if (this.saldo >= jumlah) {             // cek saldo cukup
            int saldoSementara = this.saldo;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            this.saldo = saldoSementara - jumlah;
        }
    }

    public int getSaldo() {
        return saldo;
    }
}
*/

package com.praktikum.bank;

/*
 *
 * @author alusan
 */

public class RekeningBank {
    private int saldo;

    public RekeningBank(int saldoAwal) {
        this.saldo = saldoAwal;
    }

    // synchronized: hanya satu thread yang boleh menjalankan method ini pada satu waktu (mutual exclusion)
    public synchronized void setor(int jumlah) {
        if (jumlah <= 0) {
            // Validasi input: nominal nol/negatif ditolak
            throw new IllegalArgumentException("Jumlah setor harus lebih dari 0");
        }
        int saldoSementara = this.saldo;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.saldo = saldoSementara + jumlah;

        // Bangunkan semua thread yang sedang wait() karena saldo belum cukup
        notifyAll();
    }

    public synchronized void tarikDenganTunggu(int jumlah) throws InterruptedException {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah tarik harus lebih dari 0");
        }

        // while (bukan if): kondisi dicek ulang tiap thread bangun, untuk menghindari spurious wakeup
        while (this.saldo < jumlah) {
            wait(); // thread menunggu dan melepas lock sampai ada notifyAll()
        }

        this.saldo -= jumlah;
    }

    // getSaldo juga synchronized agar nilai yang dibaca selalu yang terbaru (visibilitas memori)
    public synchronized int getSaldo() {
        return saldo;
    }
}

