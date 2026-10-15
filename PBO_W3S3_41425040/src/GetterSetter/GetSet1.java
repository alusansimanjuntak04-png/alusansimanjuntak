class GetSet {

    private String name; // Menyimpan nama

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String N) {
        this.name = N;
    }
}

class GFG {

    public static void main(String[] args) {

        // Membuat objek
        GetSet obj = new GetSet();

        // Mengisi nama
        obj.setName("Geeks for Geeks");

        // Menampilkan nama
        System.out.println(obj.getName());
    }
}
