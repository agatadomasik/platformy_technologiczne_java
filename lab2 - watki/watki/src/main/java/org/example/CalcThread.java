package org.example;

public class CalcThread implements Runnable {
    private TaskList taskList;
    private ResultList resultList;

    public CalcThread(TaskList taskQueue, ResultList resultList) {
        this.taskList = taskQueue;
        this.resultList = resultList;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                int task = taskList.getTask();
                int result = performCalculation(task);
                resultList.addResult(result);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    private int performCalculation(int number) {
        int count = countDivisors(number);
        System.out.println("Number " + number + " has " + count + " divisors.");
        return count;
    }

    private int countDivisors(int number) {
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }

}
