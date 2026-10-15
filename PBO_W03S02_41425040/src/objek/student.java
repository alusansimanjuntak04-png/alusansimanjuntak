class Student { //Asumsikan kelas Student sudah ada (tunjukkan definisinya)
    public Student () {}
}

public class Main {
    public static void main(String[] args) {
        try {
            Class <? > c = Class. forName ("Student") ;
            Student s2 = (Student) c.getDeclaredConstructor () .newInstance () ;
            System. out.println ("Object created: " + s2) ;
        } catch (ClassNotFoundException e) {
            System. err.println ("Class not found!");
        } catch (NoSuchMethodException e) {
            System. err.println ("No default constructor!");
        } catch (Exception e) {
            e.printStackTrace () ;
        }
    }
}