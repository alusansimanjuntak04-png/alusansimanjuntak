class MultipleCatch {
    public static void main(String args[]) {
        int n = args.length;
        int a = 1;
        int c[] = new int[1];
        try {
            if (n == 0)
                a = a / (a - 1);
            else
                c[21] = 5;
        } catch (ArithmeticException e) {
            System.out.println("Ups, seem out we have an Arithmetic "
                    + "Exception here...");
        } catch (Exception e) {
            System.out.println("Let the other exception handler here...");
        }
    }
}

/*
a. Dijalankan tanpa arguments: java MultipleCatch
 Catch yang dijalankan: catch (ArithmeticException e)

Output:
 Ups, seem out we have an Arithmetic Exception here...

Penjelasan:
 Karena tidak ada argument, n = 0. Perhitungan 1 / (1 - 1)
 menyebabkan pembagian dengan nol, jadi terjadi ArithmeticException.
 Error ini ditangkap oleh catch pertama.

b. Dijalankan dengan satu argument: java MultipleCatch satu
 Catch yang dijalankan: catch (Exception e)

Output:
 Let the other exception handler here...

Penjelasan:
 Karena ada satu argument, n = 1. Saat c[21] = 5 dijalankan,
 terjadi ArrayIndexOutOfBoundsException karena ukuran array hanya
 satu elemen dengan indeks 0. Catch pertama dilewati, lalu error
 ditangkap oleh catch (Exception e).

Pemahaman:
 Satu blok try bisa punya beberapa catch. Java memeriksa catch
 dari atas ke bawah dan menjalankan catch pertama yang cocok
 dengan exception yang terjadi.
*/