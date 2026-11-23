public class HandlingException {
    public static void main(String[] args) {
        try {
            System.out.println(3/0);
        } catch (ArithmeticException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
}

/*
Output:
 Error / by zero

Pengamatan:
 getMessage() cuma menampilkan pesan error-nya saja, yaitu "/ by zero".
 Nama exception-nya tidak ikut muncul. Beda dengan toString() yang
 menampilkan nama exception sekaligus pesan error-nya.
    */
