class ThrowsDemo {
    static void throwOne(){
        System.out.println("I'm throwing a non Runtime exception that I "
                + "don't handle");
        System.out.println("Without specifying in method declaration");
        throw new ArithmeticException("demo");
    }

    public static void main (String args[]){
        throwOne();
    }

}

/*
Hasil:
 Program berhasil di-compile, tapi berhenti saat dijalankan
 karena ada exception yang tidak ditangani.

Output:
 I'm throwing a non Runtime exception that I don't handle
 Without specifying in method declaration
 Exception in thread "main" java.lang.ArithmeticException: demo
     at ThrowsDemo.throwOne(ThrowsDemo.java:30)
     at ThrowsDemo.main(ThrowsDemo.java:34)

Penjelasan:
 - ArithmeticException termasuk unchecked exception, jadi Java
   tidak mewajibkan try-catch atau throws saat compile.
 - Saat throwOne() dijalankan, method tersebut melempar
   ArithmeticException dengan pesan "demo".
 - Karena tidak ada catch yang menangani exception ini, error
   diteruskan ke JVM dan program berhenti.
 - Checked exception wajib ditangani atau dideklarasikan, sedangkan
   unchecked exception tidak wajib.
 - Contoh perbaikannya ada di folder "perbaikan".
*/
