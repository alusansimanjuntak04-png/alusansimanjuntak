import java.util.Timer;
import java.util.TimerTask;

/**
 * Tugas 1 - Program reminder waktu.
 * Menampilkan "Task scheduled.", lalu setelah 15 detik menampilkan "time's up!".
 * Penundaan 15 detik ditangani sepenuhnya oleh Timer.
 */
public class Reminder {

    private final Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        // Jadwalkan RemindTask agar dieksekusi SEKALI setelah 'seconds' detik
        timer.schedule(new RemindTask(), seconds * 1000L);
    }

    // Tugas yang dijalankan oleh timer ketika waktu habis
    class RemindTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("time's up!");
            timer.cancel(); // hentikan thread timer agar program berhenti
        }
    }

    public static void main(String[] args) {
        new Reminder(15);
        System.out.println("Task scheduled.");
    }
}
