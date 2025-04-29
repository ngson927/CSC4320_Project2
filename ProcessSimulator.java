import java.io.*;
import java.util.*;

public class ProcessSimulator {
    public static void main(String[] args) {
        List<ProcessThread> processes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("processes.txt"))) {
            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int pid = Integer.parseInt(parts[0]);
                int arrivalTime = Integer.parseInt(parts[1]);
                int burstTime = Integer.parseInt(parts[2]);
                int priority = Integer.parseInt(parts[3]);

                ProcessThread pt = new ProcessThread(pid, arrivalTime, burstTime, priority);
                processes.add(pt);
            }

            for (ProcessThread pt : processes) pt.start();
            for (ProcessThread pt : processes) pt.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("All processes completed.");
    }
}
