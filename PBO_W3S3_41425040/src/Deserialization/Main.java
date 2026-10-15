import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Class Student bisa disimpan karena menggunakan Serializable
class Student implements Serializable {

    // Nama student
    String name;

    // Constructor untuk mengisi nama
    public Student(String name) {
        this.name = name;
    }

    // Menampilkan data Student
    @Override
    public String toString() {
        return "Student name: " + name;
    }
}

// Class utama
public class Main {

    public static void main(String[] args) {

        // Serialization: menyimpan object ke file
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("student.ser"))) {

            // Menyimpan object Student ke file
            out.writeObject(new Student("Alice"));

        } catch (IOException e) {

            // Menampilkan error jika proses penyimpanan gagal
            e.printStackTrace();
        }

        // Deserialization: mengambil object dari file
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("student.ser"))) {

            // Membaca object dari file
            Student s = (Student) in.readObject();

            // Menampilkan object yang dibaca
            System.out.println(s);

        } catch (Exception e) {

            // Menampilkan error jika proses pembacaan gagal
            e.printStackTrace();
        }
    }
}