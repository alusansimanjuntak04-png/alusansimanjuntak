class ReThrowExc {
    static void demoThrow() {
        try {
            System.out.println("I'm throwing an exception from demoThrow "
                    + "method");
            throw new NullPointerException("demo");
        } catch (NullPointerException e) {
            System.out.println("I'm catching the exception [" + e + "]");
            System.out.println("But I'll throw it again...\n");
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

Jawaban:
 Tidak, exception tidak akan ditangkap lagi di main.

Penjelasan:
 - Exception sudah ditangkap oleh catch di dalam demoThrow().
   Setelah itu, method selesai dan kembali ke main seperti biasa.
 - Karena exception sudah ditangani, catch di main tidak dijalankan.
   Jadi, teks "thrown from demoThrow method" tidak akan muncul.
 - Kalau ingin main ikut menangkap exception tersebut, tambahkan
   throw e; di dalam catch pada demoThrow().
 - Teks "But I'll throw it again..." tetap muncul karena baris
   throw e; sudah dihapus, jadi exception sebenarnya tidak dilempar lagi.
*/
