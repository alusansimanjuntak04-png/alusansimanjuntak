// Class Student
public class Student {

    // Method toString() untuk mengatur teks object
    @Override
    public String toString() {
        return "Student object";
    }

    // Method utama program
    public static void main(String[] args) {

        // Membuat objek Student
        Student s = new Student();

        // Memanggil method toString()
        System.out.println(s.toString());
    }
}