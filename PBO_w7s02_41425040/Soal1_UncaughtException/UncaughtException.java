class UncaughtException {
    public static void main(String args[]) {
        System.out.println(3/0);
    }
}
/*
a. Analisis
Program bisa di-compile, karena ArithmeticException termasuk
unchecked exception, tidak wajib pakai try-catch atau throws.
Paling compiler cuma kasih warning karena ada pembagian dengan nol.

Saat dijalankan, 3/0 menyebabkan ArithmeticException karena angka
tidak bisa dibagi dengan nol. Karena tidak ada try-catch yang
menangani error ini, JVM akan menampilkan pesan error dan lokasi
baris yang bermasalah, lalu program berhenti.

Error terjadi sebelum println() selesai dijalankan, jadi tidak ada
angka yang berhasil dicetak.

b. Output
Exception in thread "main" java.lang.ArithmeticException: / by zero
         at UncaughtException.main(UncaughtException.java:28)

 Pesan error ini ditampilkan oleh JVM, bukan dari program kita.
*/
