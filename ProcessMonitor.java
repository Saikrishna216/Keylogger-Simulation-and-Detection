import java.util.stream.Stream;

public class ProcessMonitor {
    public static void checkRunningProcesses() {
        System.out.println("Checking running processes...");

        Stream<ProcessHandle> processes = ProcessHandle.allProcesses();
        processes.forEach(process -> {
            ProcessHandle.Info info = process.info();
            info.command().ifPresent(command -> {
                if (isSuspiciousProcess(command)) {
                    System.out.println("[ALERT] Suspicious Process Detected: " + command);
                }
            });
        });
    }

    private static boolean isSuspiciousProcess(String processName) {
        // List of common keyloggers or suspicious processes
        String[] knownKeyloggers = {"keylogger", "spybot", "logkeys", "hooklogger"};
        for (String keylogger : knownKeyloggers) {
            if (processName.toLowerCase().contains(keylogger)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        checkRunningProcesses();
    }
}
