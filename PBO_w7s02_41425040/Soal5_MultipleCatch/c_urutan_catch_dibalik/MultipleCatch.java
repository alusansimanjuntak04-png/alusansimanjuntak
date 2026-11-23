class MultipleCatch {
    public static void main(String args[]) {
        int n = args.length;
        int a = 1;
        int c[] = new int[1];
        try {
            if (n == 0)
                a = a / (a - 1);
            else
                c[21] = 5;
        } catch (Exception e) {
            System.out.println("Ups, seem out we have an Arithmetic "
                    + "Exception here...");
        } catch (ArithmeticException e) {
            System.out.println("Let the other exception handler here...");
        }
    }
}

/*
Hasil:
 Kode tidak bisa di-compile karena urutan catch-nya salah.

Error:
 exception ArithmeticException has already been caught

Penjelasan:
 - Exception adalah induk dari ArithmeticException. Jadi, catch
   (Exception e) yang ditulis lebih dulu sudah menangkap
   ArithmeticException juga.
 - Akibatnya, catch (ArithmeticException e) di bawahnya tidak akan
   pernah dijalankan. Java menganggapnya sebagai error saat compile.
 - Solusinya, tulis catch yang lebih spesifik terlebih dahulu,
   lalu catch yang lebih umum di bagian bawah.
*/
