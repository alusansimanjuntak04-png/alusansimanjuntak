// Interface untuk kendaraan yang berjalan di darat
interface LandVehicle {
    // Menampilkan info bahwa ini kendaraan darat
    default void landInfo() {
        System.out.println("This is a LandVehicle");
    }
}

// Interface untuk kendaraan yang berjalan di air
interface WaterVehicle {
    // Menampilkan info bahwa ini kendaraan air
    default void waterInfo() {
        System.out.println("This is a WaterVehicle");
    }
}

// Class ini punya kemampuan dari LandVehicle dan WaterVehicle
class AmphibiousVehicle implements LandVehicle, WaterVehicle {

    // Constructor, dijalankan saat objek dibuat
    AmphibiousVehicle() {
        System.out.println("This is an AmphibiousVehicle");
    }
}

// Class utama untuk menjalankan program
public class Test {
    public static void main(String[] args) {

        // Membuat objek AmphibiousVehicle
        AmphibiousVehicle obj = new AmphibiousVehicle();

        // Memanggil fungsi info kendaraan air
        obj.waterInfo();

        // Memanggil fungsi info kendaraan darat
        obj.landInfo();
    }
}