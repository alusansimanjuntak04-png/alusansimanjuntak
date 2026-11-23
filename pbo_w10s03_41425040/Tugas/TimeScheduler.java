import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Tugas 2 - Menampilkan tanggal dan waktu saat ini dengan selisih 5 detik
 * antara "mulai" dan "berakhir".
 * Menggunakan Timer, TimerTask, Date, dan method scheduleAtFixedRate.
 */
public class TimeScheduler {

    public static void main(String[] args) {
        final Timer timer = new Timer();

        System.out.println("Program started");

        TimerTask task = new TimerTask() {
            private int tick = 0; // penghitung eksekusi (0..5)

            @Override
            public void run() {
                if (tick % 2 == 0) {
                    System.out.println("Time started at:" + new Date());
                } else {
                    System.out.println("Time finished at:" + new Date());
                }
                tick++;

                // 3 siklus (mulai + berakhir) = 6 kali eksekusi, lalu berhenti
                if (tick == 6) {
                    timer.cancel();
                    System.out.println("Program cancelled");
                }
            }
        };

        // delay awal 0 ms, lalu diulang tiap 5000 ms (5 detik) pada laju tetap
        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}
