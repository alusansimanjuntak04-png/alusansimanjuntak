class CaughtExceptionFinally {
    public static void main(String args[]) {
        int n = args.length;
        try {
            if (n == 0) {
                System.out.println(3/n);
                System.out.println("This won't be executed");
            } else {
                System.out.println("This will be executed if "
                        + "arguments is not zero");
            }
        } catch (NullPointerException e) {
            System.out.println("Catching exception: " + e);
        } finally {
            System.out.println("After trying or catching...");
        }
    }
}

/*
Dijalankan tanpa arguments: java CaughtExceptionFinally
 Output:
  After trying or catching...
  Exception in thread "main" java.lang.ArithmeticException: / by zero
     at CaughtExceptionFinally.main(CaughtExceptionFinally.java:38)

Hasil:
 Program berhasil di-compile, tapi saat dijalankan terjadi error.
 Urutan output di terminal bisa berbeda karena dicetak ke stdout
 dan stderr.

 Dijalankan dengan arguments: java CaughtExceptionFinally satu
  Output:
   This will be executed if arguments is not zero
   After trying or catching...
   Tidak ada exception karena n tidak sama dengan 0.

  Penjelasan:
   - Exception yang terjadi adalah ArithmeticException, sedangkan
     catch hanya menangani NullPointerException. Jadi, exception
     tersebut tidak bisa ditangkap oleh catch itu.
   - Karena tidak ada catch yang cocok, program tidak bisa menangani
     exception tersebut.
   - Blok finally tetap dijalankan sebelum program berhenti.
   - Setelah itu, JVM menampilkan pesan error dan stack trace,
     lalu program berhenti.
   - Kesimpulan: catch hanya bisa menangkap exception yang sesuai
     dengan tipe yang ditulis atau turunannya.
*/