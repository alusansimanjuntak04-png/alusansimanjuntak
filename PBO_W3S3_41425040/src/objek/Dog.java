public class Dog {
    String name;
    String breed;
    int age;
    String color;

    // Constructor
    public Dog(String name, String breed, int age, String color) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
    }

    // Method 1
    public String getName() {
        return name;
    }

    // Method 2
    public String getBreed() {
        return breed;
    }

    // Method 3
    public int getAge() {
        return age;
    }

    // Method 4
    public String getColor() {
        return color;
    }

    // Method toString
    @Override
    public String toString() {
        return "Name is: " + this.getName()
                + "\nBreed, age, and color are: "
                + this.getBreed() + ", "
                + this.getAge() + ", "
                + this.getColor();
    }

    // Main method
    public static void main(String[] args) {
        Dog tuffy = new Dog("Tuffy", "Papillon", 5, "White");

        System.out.println(tuffy.toString());
    }
}