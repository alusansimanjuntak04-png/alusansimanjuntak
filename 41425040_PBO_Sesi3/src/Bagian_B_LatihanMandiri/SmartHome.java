package Bagian_B_LatihanMandiri;

// Interface untuk mengatur daya perangkat
interface Switchable {
    void turnOn();
    void turnOff();
}

// Interface untuk koneksi WiFi
interface Connectable {
    void connectToWifi(String ssid, String ipAddress);
}


// Class SmartLamp
class SmartLamp implements Switchable, Connectable {

    // Atribut private (encapsulation)
    private int brightness;
    private boolean isOn;
    private boolean connectedToWifi;

    // Constructor
    public SmartLamp(int brightness) {
        this.brightness = brightness;
        this.isOn = false;
        this.connectedToWifi = false;
    }

    // Menyalakan lampu
    @Override
    public void turnOn() {
        isOn = true;

        System.out.println("[Lampu Pintar] Dinyalakan dengan kecerahan "
                + brightness + "%");
    }

    // Mematikan lampu
    @Override
    public void turnOff() {
        isOn = false;
        connectedToWifi = false;

        System.out.println("[Lampu Pintar] Dimatikan.");
    }

    // Menghubungkan lampu ke WiFi
    @Override
    public void connectToWifi(String ssid, String ipAddress) {

        // Tidak boleh terhubung jika perangkat mati
        if (!isOn) {
            System.out.println(
                    "[Error] Lampu Pintar tidak dapat terhubung ke WiFi "
                    + "karena perangkat masih mati."
            );
            return;
        }

        connectedToWifi = true;

        System.out.println("[WiFi Device] Terhubung ke jaringan: "
                + ssid + " (IP: " + ipAddress + ")");
    }
}


// Class SmartAC
class SmartAC implements Switchable, Connectable {

    // Atribut private (encapsulation)
    private double temperature;
    private boolean isOn;
    private boolean connectedToWifi;

    // Constructor
    public SmartAC(double temperature) {
        this.temperature = temperature;
        this.isOn = false;
        this.connectedToWifi = false;
    }

    // Menyalakan AC
    @Override
    public void turnOn() {
        isOn = true;

        System.out.println("[AC Pintar] Dinyalakan pada suhu "
                + temperature + " C");
    }

    // Mematikan AC
    @Override
    public void turnOff() {
        isOn = false;
        connectedToWifi = false;

        System.out.println("[AC Pintar] Dimatikan.");
    }

    // Menghubungkan AC ke WiFi
    @Override
    public void connectToWifi(String ssid, String ipAddress) {

        // Tidak boleh terhubung jika perangkat mati
        if (!isOn) {
            System.out.println(
                    "[Error] AC Pintar tidak dapat terhubung ke WiFi "
                    + "karena perangkat masih mati."
            );
            return;
        }

        connectedToWifi = true;

        System.out.println("[WiFi Device] Terhubung ke jaringan: "
                + ssid + " (IP: " + ipAddress + ")");
    }
}


// CLASS UTAMA
public class SmartHome {

    public static void main(String[] args) {

        // Membuat objek lampu pintar
        SmartLamp lampu = new SmartLamp(80);

        // Membuat objek AC pintar
        SmartAC ac = new SmartAC(22);


        // =========================
        // LAMPU PINTAR
        // =========================

        lampu.turnOn();

        lampu.connectToWifi(
                "Rumah_Indah_5G",
                "192.168.1.50"
        );


        // =========================
        // AC PINTAR
        // =========================

        ac.turnOn();

        ac.connectToWifi(
                "Rumah_Indah_5G",
                "192.168.1.51"
        );


        // Mematikan AC
        ac.turnOff();
    }
}
