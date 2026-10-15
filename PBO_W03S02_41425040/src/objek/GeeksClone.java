package objek;

class Geeks implements Cloneable {
    @Override //method 1
    protected Object clone ()
        throws CloneNotSupportedException
}

return super.clone () ;// Super () mengacu ke parent class

String name = "GeeksForGeeks";

public static void main(String[] args)// Method 2

Geeks ol = new Geeks () ;

try {
Geeks o2 = (Geeks) ol.clone () ;
System. out.print1n (o2.name) ;

catch (CloneNotSupportedException e) {
e.printStackTrace () ;