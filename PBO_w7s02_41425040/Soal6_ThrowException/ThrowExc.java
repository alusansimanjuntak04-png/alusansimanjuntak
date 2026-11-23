class ThrowExc {
    public static void main(String args[]) {
        try {
            System.out.println("I'm throwing an exception from here...");
            throw new NullPointerException("throw demo");
        } catch (NullPointerException e) {
            System.out.println("I'm catching an exception: [" + e + "] "
                    + "from here...");
        }
    }
}

/*
Output:
 I'm throwing an exception from here...
 I'm catching an exception: [java.lang.NullPointerException: throw demo] from here...

Pengamatan:
 - Exception bukan cuma muncul otomatis dari Java, tapi bisa juga
   dibuat dan dilempar sendiri pakai kata kunci throw.
 - Baris throw new NullPointerException("throw demo") membuat
   exception dengan pesan "throw demo", lalu langsung pindah ke catch.
 - Catch menangkap exception tersebut. Tulisan "[" + e + "]"
   menampilkan nama exception beserta pesan error-nya.
*/
