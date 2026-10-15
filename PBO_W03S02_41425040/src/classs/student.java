class Student {
    int id;
    String n;

    public Student(int id, String n) {
        this.id = id;
        this.n = n;
    }
}

public class Main {
    public static void main(String[] args) {

        Student sl = new Student (10, "Alice");

        System. out.println(sl.id);
        System. out.println (sl.n) ;
    }
}