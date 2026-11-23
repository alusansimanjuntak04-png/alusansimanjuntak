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
        } catch (Exception e) {
            System.out.println("Catching exception: " + e);
        } finally {
            System.out.println("After trying or catching...");
        }
    }
}

/*
 a. Dijalankan tanpa arguments: java CaughtExceptionFinally
 Output:
 Catching exception: java.lang.ArithmeticException: / by zero
 After trying or catching...

 Penjelasan:
 Karena tidak ada arguments, args.length = 0 dan n = 0.
 Saat 3/n dijalankan, terjadi ArithmeticException dan program
 langsung masuk ke blok catch. Tulisan "This won't be executed"
 dilewati. Setelah itu, blok finally tetap dijalankan.

 b. Dijalankan dengan arguments: java CaughtExceptionFinally satu
 Output:
 This will be executed if arguments is not zero
 After trying or catching...

 Penjelasan:
 Karena ada satu argument, args.length = 1 dan n = 1.
 Program masuk ke blok else, jadi tidak ada exception dan blok
 catch dilewati. Blok finally tetap dijalankan.

 c. Kesimpulan:
 - Try berisi kode yang bisa menyebabkan exception. Kalau terjadi
   exception, sisa kode di dalam try dilewati.
 - Catch dijalankan kalau ada exception yang sesuai.
 - Finally tetap dijalankan, baik terjadi exception maupun tidak.
   Blok ini biasanya dipakai untuk membersihkan resource, seperti
   menutup file atau koneksi.
*/
