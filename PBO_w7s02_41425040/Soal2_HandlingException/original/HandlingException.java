public class HandlingException {
    public static void main(String[] args) {
        try {
            System.out.println(3/0);
        } catch (ArithmeticException ex) {
            System.out.println("Error " + ex);
        }
    }
}

/*
Output:
 Error java.lang.ArithmeticException: / by zero

 Pengamatan:
 Pembagian 3/0 menyebabkan ArithmeticException, lalu error ini
 ditangkap oleh catch (ArithmeticException ex). Jadi, program
 tidak langsung berhenti. Tulisan "Error " + ex menampilkan
 nama exception dan pesan error-nya.
*/
