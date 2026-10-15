public class Student {

    int roll;

    // Membandingkan roll dari dua object
    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            return this.roll == ((Student) o).roll;
        }

        return false;
    }

    // Method utama
    public static void main(String[] args) {

        // Membuat object Student pertama
        Student s1 = new Student();
        s1.roll = 101;

        // Membuat object Student kedua
        Student s2 = new Student();
        s2.roll = 101;

        // Membuat object Student ketiga
        Student s3 = new Student();
        s3.roll = 102;

        // Membandingkan s1 dengan s2
        System.out.println("s1.equals(s2) : " + s1.equals(s2));

        // Membandingkan s1 dengan s3
        System.out.println("s1.equals(s3) : " + s1.equals(s3));
    }
}