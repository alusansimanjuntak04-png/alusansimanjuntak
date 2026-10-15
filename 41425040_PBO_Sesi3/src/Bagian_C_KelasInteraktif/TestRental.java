/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bagian_C_KelasInteraktif;

/**
 *
 * @author user
 */


import id.ac.del.rental.VehicleRental; 

public class TestRental { 
    public static void main(String[] args) { 
        VehicleRental v = new VehicleRental("BK 1234 DEL", 250000); 
        
        // Baris A 
        v.plateNumber = ""; 
        
        // Baris B 
        v.rentalRatePerDay = -500000; 
        
        // Baris C 
        v.discountRate = 0.20; 
        
        // Baris D 
        v.setRentalDays(-7); 
        
        System.out.println("Total Biaya: Rp" + v.getTotalCost()); 
    } 
} 

/*
### 1) Analisis Kompilasi dan Hak Akses

**i)** Saat `TestRental.java` dijalankan, **Baris B dan C akan error** karena `TestRental` dan `VehicleRental` ada di package yang berbeda.

* **Baris A** tidak error karena `plateNumber` bersifat `public`, jadi bisa diakses dari mana saja.
* **Baris B** error karena `rentalRatePerDay` bersifat `protected`. Kalau beda package, atribut ini tidak bisa dipanggil langsung oleh class biasa seperti `TestRental`.
* **Baris C** error karena `discountRate` tidak punya access modifier, jadi termasuk **default**. Artinya hanya bisa dipakai oleh class yang ada di package yang sama.
* **Baris D** tidak error karena `setRentalDays()` bersifat `public`, jadi bisa dipanggil walaupun beda package.
* Kalau `private`, atribut tersebut hanya bisa digunakan di dalam class `VehicleRental` sendiri.

Jadi gampangnya, `public` bebas diakses, `protected` ada batasannya, `default` hanya satu package, dan `private` hanya di class sendiri.


### 2) Kelemahan Integritas Data

**i)** Masalah pada Baris D adalah walaupun sudah menggunakan setter:

v.setRentalDays(-7);

method tersebut tidak mengecek apakah nilai yang dimasukkan benar atau tidak. Jadi nilai `-7` tetap bisa disimpan.

Seharusnya setter dibuat seperti ini:

public void setRentalDays(int days) {
    if (days < 0) {
        System.out.println("Jumlah hari tidak boleh negatif.");
        return;
    }

    this.rentalDays = days;
}

Dengan begitu, nilai `-7` tidak akan masuk ke `rentalDays`.

**ii)** Total biaya bisa menjadi negatif karena jumlah hari sewa bisa bernilai negatif. Misalnya harga sewa `250000` dan jumlah hari `-7`, hasil perkaliannya juga akan negatif.

Supaya data aman, atribut seperti `rentalRatePerDay`, `rentalDays`, dan `discountRate` sebaiknya dibuat `private`. Setelah itu, gunakan setter yang melakukan pengecekan.

Contohnya:

* Harga sewa tidak boleh negatif.
* Jumlah hari tidak boleh negatif.
* Diskon harus berada pada nilai yang sesuai, misalnya `0` sampai `1`.

Dengan cara ini, data tidak bisa sembarangan diisi dengan nilai yang salah.



### 3) Penerapan Interface (Dependency Injection)

**i)** Untuk membuat beberapa jenis diskon, kita bisa membuat interface `Discountable`:

public interface Discountable {
    double calculateDiscount(double amount);
}

Nanti `MemberDiscount`, `FlashSaleDiscount`, dan `CorporateDiscount` bisa menggunakan interface tersebut dan punya cara hitung diskon masing-masing.

**ii)** Method `getTotalCost()` bisa dibuat seperti ini:

public double getTotalCost(Discountable discount) {
    double subtotal = rentalRatePerDay * rentalDays;

    double potongan = discount.calculateDiscount(subtotal);

    return subtotal - potongan;
}

Kemudian dari `TestRental` bisa dipanggil seperti ini:

Discountable diskon = new MemberDiscount();

double total = v.getTotalCost(diskon);

System.out.println("Total Biaya: Rp" + total);

Kalau ingin menggunakan diskon lain, tinggal ganti objeknya, misalnya:

Discountable diskon = new FlashSaleDiscount();

Jadi `VehicleRental` tidak perlu diubah-ubah untuk setiap jenis diskon. Program akan otomatis memakai cara perhitungan dari objek diskon yang diberikan. Ini merupakan **polimorfisme** dan **dependency injection**.

*/