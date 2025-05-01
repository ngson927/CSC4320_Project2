import java.io.*;
import java.util.*;

/**
 * This class simulates running processes by reading their data from a file
 * and creating threads for each one. Each process runs as its own thread.
 */
public class ProcessSimulator {
    public static void main(String[] args) {
        // We'll keep all our process threads in this list
        List<ProcessThread> processes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("processes.txt"))) {
            String line = br.readLine(); // Skip the first line (it's probably a header)

            // Read each line from the file and turn it into a ProcessThread
            while ((line = br.readLine()) != null) {
                // Trim any extra spaces and split the line into parts
                String[] parts = line.trim().split("\\s+");

                // Each line is expected to have 4 pieces of info: PID, arrival, burst, priority
                int pid = Integer.parseInt(parts[0]);
                int arrivalTime = Integer.parseInt(parts[1]);
                int burstTime = Integer.parseInt(parts[2]);
                int priority = Integer.parseInt(parts[3]);

                // Create a new process with that info and add it to the list
                ProcessThread pt = new ProcessThread(pid, arrivalTime, burstTime, priority);
                processes.add(pt);
            }

            // Start each process thread — they’ll begin running on their own
            for (ProcessThread pt : processes) pt.start();

            // Wait for *all* threads to finish before we print the final message
            for (ProcessThread pt : processes) pt.join();

        } catch (Exception e) {
            // If anything goes wrong (e.g. file not found or format is off), print the error
            e.printStackTrace();
        }

        // Once all threads are done, we're officially done
        System.out.println("All processes completed.");
    }
}
