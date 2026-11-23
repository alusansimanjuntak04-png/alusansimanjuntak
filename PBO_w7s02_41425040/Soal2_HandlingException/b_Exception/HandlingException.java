public class HandlingException {
    public static void main(String[] args) {
        try {
            System.out.println(3/0);
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }
}

/*
Output:
 Error java.lang.ArithmeticException: / by zero

Penjelasan:
 Exception adalah induk dari semua exception, termasuk
 ArithmeticException. Jadi, catch (Exception ex) bisa menangkapnya
 karena ArithmeticException termasuk turunan Exception.

 Walaupun ditangkap pakai Exception, jenis error yang ditampilkan
 tetap ArithmeticException.

 Tapi, catch (Exception) terlalu umum karena bisa menangkap banyak
 jenis error. Lebih baik tangkap jenis exception yang sesuai agar
 kesalahan lebih mudah diketahui.
*/
