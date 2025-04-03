import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;

public class FileIOMonitor {
    public static void monitorFileIO(String directory) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(directory);
        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);

        System.out.println("Monitoring I/O operations in " + directory);

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("[ALERT] File I/O detected: " + event.kind() + " - " + event.context());
            }
            key.reset();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        monitorFileIO("C:/Users/Public/"); // Change this to a relevant directory
    }
}