class Student {

    // Constructor kosong (default constructor)
    public Student() {
    }

    // Agar object bisa ditampilkan dengan jelas
    @Override
    public String toString() {
        return "Student object";
    }
}

// Class utama
public class Main {

    public static void main(String[] args) {

        try {
            // Mencari class Student berdasarkan nama class
            Class<?> c = Class.forName("Student");

            // Membuat object Student menggunakan constructor kosong
            Student s2 = (Student) c.getDeclaredConstructor().newInstance();

            // Menampilkan object yang berhasil dibuat
            System.out.println("Object created: " + s2);

        } catch (ClassNotFoundException e) {

            // Jika class Student tidak ditemukan
            System.err.println("Class not found!");

        } catch (NoSuchMethodException e) {

            // Jika constructor kosong tidak ditemukan
            System.err.println("No default constructor!");

        } catch (Exception e) {

            // Menampilkan error lainnya
            e.printStackTrace();
        }
    }
}