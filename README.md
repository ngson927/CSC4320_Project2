
# Operating Systems Project 2

## Thread-Based Process Simulation and Synchronization

### Course
Operating Systems 4320 (Spring 2025)

### Project Overview
This project simulates real-time process execution using Java threads and implements a classic synchronization problem using mutexes and semaphores.

### Features
- Simulated process scheduling from a `processes.txt` file.
- Each process modeled as an independent Java thread with simulated arrival and burst times.
- Implemented the **Producer-Consumer Problem** with a bounded buffer using semaphores and a mutex lock (`ReentrantLock`).
- Printed detailed thread activity logs (start, wait, produce, consume, finish).

### Files Included
- `processes.txt` — Input file with process information (PID, Arrival Time, Burst Time, Priority).
- `ProcessThread.java` — Class representing a simulated process.
- `ProcessSimulator.java` — Main program to create and run all processes.
- `BoundedBuffer.java` — Class representing the shared bounded buffer for producer-consumer.
- `SyncDemo.java` — Main program demonstrating synchronization using producer-consumer.
- `README.md` — Project documentation.
- `OS_Project2_Report.pdf` — Final project report (to be included).

### How to Run
1. **Compile the Java files**:
   ```bash
   javac ProcessThread.java ProcessSimulator.java BoundedBuffer.java SyncDemo.java
   ```

2. **Run Process Simulation**:
   ```bash
   java ProcessSimulator
   ```

3. **Run Synchronization Demo (Producer-Consumer)**:
   ```bash
   java SyncDemo
   ```

### Requirements
- Java Development Kit (JDK) 17 or later.
- Any IDE like VS Code, IntelliJ, Eclipse, or simply Terminal.

### Lessons Learned
- Multithreading in Java.
- Simulation of process scheduling in an OS.
- Solving synchronization issues with semaphores and locks.
- Managing thread safety and avoiding race conditions.

### Author
- Son Nguyen
- Sheila Corona
- Harry Cao

---
