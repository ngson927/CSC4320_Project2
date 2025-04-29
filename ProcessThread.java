public class ProcessThread extends Thread {
    int pid, arrivalTime, burstTime, priority;

    public ProcessThread(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public void run() {
        try {
            Thread.sleep(arrivalTime * 1000);  // simulate arrival delay
            System.out.println("[Process " + pid + "] Starting (Priority " + priority + ")");
            Thread.sleep(burstTime * 1000);    // simulate CPU burst
            System.out.println("[Process " + pid + "] Finished");
        } catch (InterruptedException e) {
            System.err.println("[Process " + pid + "] Interrupted");
        }
    }
}
