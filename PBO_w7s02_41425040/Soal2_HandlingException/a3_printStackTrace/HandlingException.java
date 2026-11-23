// Output (dicetak ke System.err):
// java.lang.ArithmeticException: / by zero
//     at HandlingException.main(HandlingException.java:14)
//
// Pengamatan:
// printStackTrace() menampilkan jenis exception, pesan error, dan lokasi
// terjadinya error, termasuk nama file dan nomor barisnya. Jadi, cara ini
// lebih lengkap dan membantu saat mencari kesalahan program dibandingkan
// getMessage() yang cuma menampilkan pesan error.
public class HandlingException {
    public static void main(String[] args) {
        try {
            System.out.println(3/0);
        } catch (ArithmeticException ex) {
            ex.printStackTrace();
        }
    }
}
