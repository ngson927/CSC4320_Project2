/**
 * This class represents a single process as a thread.
 * Each one will "arrive," do its CPU work (aka burst), then finish.
 */
public class ProcessThread extends Thread {
    int pid, arrivalTime, burstTime, priority;

    /**
     * Set up the process with its properties.
     * 
     * @param pid          the process ID (just a label to identify it)
     * @param arrivalTime  how long we should wait before starting (in seconds)
     * @param burstTime    how long the process needs the CPU (in seconds)
     * @param priority     the priority level (not used here, but tracked)
     */
    public ProcessThread(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    /**
     * What the thread actually does when it starts running.
     * It "arrives," announces itself, does its work, and then says it’s done.
     */
    public void run() {
        try {
            // Wait before starting to simulate the process arriving later
            Thread.sleep(arrivalTime * 1000);

            System.out.println("[Process " + pid + "] Starting (Priority " + priority + ")");

            // Simulate how long the process uses the CPU
            Thread.sleep(burstTime * 1000);

            System.out.println("[Process " + pid + "] Finished");

        } catch (InterruptedException e) {
            // In case the thread gets interrupted while waiting or running
            System.err.println("[Process " + pid + "] Interrupted");
        }
    }
}
