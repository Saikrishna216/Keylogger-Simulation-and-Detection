import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KeyloggerDetector {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);


        // Run process monitor
        executor.submit(ProcessMonitor::checkRunningProcesses);

        // Run file I/O monitor
        executor.submit(() -> {
            try {
                FileIOMonitor.monitorFileIO("C:/Users/Public/");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}
