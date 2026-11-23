public class HandlingException {
    public static void main(String[] args) {
        try {
            System.out.println(3/0);
        } catch (ArithmeticException ex) {
            System.out.println("Error " + ex.getStackTrace());
        }
    }
}

/*
Output:
 Error [Ljava.lang.StackTraceElement;@659e0bfd

Pengamatan:
 getStackTrace() menghasilkan array, bukan String. Jadi, kalau
 langsung digabung pakai tanda +, yang muncul cuma tipe array dan
 kode uniknya, bukan isi stack trace. Kalau mau melihat isinya,
 bisa pakai perulangan atau langsung panggil ex.printStackTrace().
*/
