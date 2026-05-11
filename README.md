# Pi Monte Carlo Estimator 🎯

A Java-based application that estimates the value of Pi using the **Monte Carlo method** and the **Fork/Join framework**. This project demonstrates how to efficiently parallelize massive computational tasks using a divide-and-conquer strategy.

## 🎯 About the Project

The program approximates the mathematical constant Pi by simulating the random placement of 2 billion points inside a 1x1 square. It then checks how many of these points fall within an inscribed quarter-circle (using the condition `x*x + y*y <= 1.0`). The ratio of points inside the circle to the total number of points is then multiplied by 4 to estimate Pi.

Because generating and validating 2,000,000,000 points sequentially is computationally expensive, the project leverages Java's `ForkJoinPool`. The main workload is recursively split into smaller, manageable chunks (down to a threshold of 5,000,000 points) that are processed concurrently across all available CPU cores.

## 🧠 Key Concepts Explored

*   **Monte Carlo Method:** A statistical simulation technique used to approximate complex mathematical values through random sampling.
*   **Fork/Join Framework:** Using `ForkJoinPool` and `RecursiveTask` to implement a highly efficient, multi-threaded divide-and-conquer algorithm.
*   **Work Stealing:** The underlying mechanism of the Fork/Join pool where idle threads "steal" pending tasks from busy threads, maximizing CPU utilization.
*   **Thread-Safe Randomness:** Utilizing `ThreadLocalRandom` to generate random numbers concurrently without the performance bottleneck of thread contention.

## 🚀 Compilation & Execution

### 1. Compilation
Navigate to the directory containing the source file and compile it:

```bash
javac projekt2.java
