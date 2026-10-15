public class hashCode{

    int roll;

    @Override
    public int hashCode () {
        return roll;
    }

    public static void main(String[] args) {
        Student sl = new Student () ;
        sl.roll = 101;

        Student s2 = new Student ();
        s2.roll = 102;

        System. out.println ("HashCode s1: " + sl.hashCode () ) ;
        System. out.println ("HashCode s2: " + s2.hashCode () ) ;
    }
}