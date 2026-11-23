class ThrowsDemo {
    static void throwOne(){
        System.out.println("I'm throwing a non Runtime exception that I "
                + "don't handle");
        System.out.println("Without specifying in method declaration");
        throw new IllegalAccesException("demo");
    }

    public static void main (String args[]){
        throwOne();
    }

}

/*
Hasil:
 Kode tidak bisa di-compile, jadi program tidak bisa dijalankan.

Penyebab:
 - Nama IllegalAccesException salah ketik. Seharusnya
   IllegalAccessException, pakai dua huruf "s" pada Access.
 - Kalau namanya sudah diperbaiki, kode masih error saat compile
   karena IllegalAccessException termasuk checked exception.
 - Checked exception wajib ditangani pakai try-catch atau
   ditambahkan throws pada method.

Perbaikan:
 Tambahkan throws IllegalAccessException pada method throwOne()
 atau gunakan try-catch untuk menangani exception tersebut.

Catatan:
 Kode yang sudah diperbaiki ada di folder "perbaikan".
*/
