class ThrowsDemo {
    static void throwOne() throws IllegalAccessException {
        System.out.println("I'm throwing a non Runtime exception that I "
                + "don't handle");
        System.out.println("Without specifying in method declaration");
        throw new IllegalAccessException("demo");
    }

    public static void main(String args[]) {
        try {
            throwOne();
        } catch (IllegalAccessException e) {
            System.out.println("Caught " + e);
        }
    }
}

/*
Output:
 I'm throwing a non Runtime exception that I don't handle
 Without specifying in method declaration
 Caught java.lang.IllegalAccessException: demo

Penjelasan perbaikan:
 - Nama exception diperbaiki menjadi IllegalAccessException.
 - Method throwOne() ditambahkan throws IllegalAccessException
   untuk memberi tahu bahwa method tersebut bisa melempar checked
   exception.
 - Di main(), exception ditangkap menggunakan try-catch.
 - Karena exception berhasil ditangani, program selesai dengan normal
   tanpa berhenti karena error.
*/
