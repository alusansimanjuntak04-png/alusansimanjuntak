class GetSet {

    private int num; // Menyimpan angka

    // Setter
    public void setNumber(int number) {

        // Validasi angka
        if (number < 1 || number > 10) {
            throw new IllegalArgumentException();
        }

        num = number; // Menyimpan angka
    }

    // Getter
    public int getNumber() {
        return num;
    }
}

class GFG {

    public static void main(String[] args) {

        // Membuat objek
        GetSet obj = new GetSet();

        // Mengisi angka
        obj.setNumber(5);

        // Menampilkan angka
        System.out.println(obj.getNumber());
    }
}