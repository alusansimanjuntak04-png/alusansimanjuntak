class ReThrowExc {
    static void demoThrow() {
        try {
            System.out.println("I'm throwing an exception from demoThrow "
                    + "method");
            throw new NullPointerException("demo");
        } catch (NullPointerException e) {
            System.out.println("I'm catching the exception [" + e + "]");
            System.out.println("But I'll throw it again...\n");
            throw e;
        }
    }

    public static void main(String args[]) {
        try {
            demoThrow();
        } catch (NullPointerException e) {
            System.out.println("I'm catching the exception [" + e + "]\n "
                    + "thrown from demoThrow method");
        }
    }
}


/*
Output:
 I'm throwing an exception from demoThrow method
 I'm catching the exception [java.lang.NullPointerException: demo]
 But I'll throw it again...

 I'm catching the exception [java.lang.NullPointerException: demo]
 thrown from demoThrow method

b. Kesimpulan:
 - Exception bisa ditangkap dulu di sebuah method, misalnya untuk
   mencetak pesan, lalu dilempar kembali pakai throw e; agar bisa
   ditangani oleh method yang memanggilnya.
 - Exception bisa merambat dari satu method ke method lain sampai
   ditemukan catch yang cocok. Kalau tidak ada, JVM yang menangani.
 - Dengan cara ini, setiap method bisa menangani bagian error yang
   sesuai tugasnya, sedangkan sisanya diserahkan ke method lain.

 Catatan:
  Jawaban 7a ada di folder a_tanpa_rethrow.
*/