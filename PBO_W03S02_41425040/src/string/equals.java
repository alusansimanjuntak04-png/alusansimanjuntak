public class equals {

    int roll;

    @Override
    public boolean equals (Object o) {
        if (o instanceof Student) {
        return this. roll == ( (Student) o) .roll;
        }
        return false;
    }

    public static void main(String[] args) {
        Student sl = new Student () ;
        sl.roll = 101;

        Student s2 = new Student () ;
        s2.roll = 101;

        Student s3 = new Student () ;
        s3.roll = 102;

        System. out.println("s1.equals (s2) : " + s1.equals (s2) ) ;
        System. out.println("s1.equals (s3) : " + s1.equals (s3) ) ;
    }
}