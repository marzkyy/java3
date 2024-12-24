import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleClock {

    public static void main(String[] args) {
        // Thread for updating the time in the background
        Thread updateTimeThread = new Thread(new TimeUpdater(), "TimeUpdaterThread");
        // Thread for displaying the time to the console
        Thread displayTimeThread = new Thread(new TimeDisplayer(), "DisplayTimeThread");

        // Setting thread priorities
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        displayTimeThread.setPriority(Thread.MAX_PRIORITY);

        // Start the threads
        updateTimeThread.start();
        displayTimeThread.start();
    }
}

class SharedClock {
    private static String currentTime;

    // Method to update the current time
    public synchronized static void updateCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        currentTime = formatter.format(new Date());
    }

    // Method to get the current time
    public synchronized static String getCurrentTime() {
        return currentTime;
    }
}

// Thread responsible for updating the current time
class TimeUpdater implements Runnable {
    @Override
    public void run() {
        while (true) {
            SharedClock.updateCurrentTime();
            try {
                // Update time every second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("TimeUpdater thread interrupted: " + e.getMessage());
            }
        }
    }
}

// Thread responsible for displaying the time to the console
class TimeDisplayer implements Runnable {
    @Override
    public void run() {
        while (true) {
            String currentTime = SharedClock.getCurrentTime();
            if (currentTime != null) {
                System.out.println("Current Time: " + currentTime);
            }
            try {
                // Refresh display every 1 second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("DisplayTime thread interrupted: " + e.getMessage());
            }
        }
    }
}
