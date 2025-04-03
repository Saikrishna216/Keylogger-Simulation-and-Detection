import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class KeyloggerSimulator {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("C:/Users/Public/logs.txt", true)) {
            Random random = new Random();
            while (true) {
                writer.write("Key pressed: " + (char) (random.nextInt(26) + 'a') + "\n");
                writer.flush();
                Thread.sleep(500); // Simulate keylogging every half second
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
