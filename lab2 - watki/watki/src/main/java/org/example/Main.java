package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(args[0]);

        TaskList taskList = new TaskList();
        ResultList resultList = new ResultList();

        Thread[] calcThreads = new Thread[n];
        for (int i = 0; i < n; i++) {
            calcThreads[i] = new Thread(new CalcThread(taskList, resultList));
            calcThreads[i].start();
        }

        System.out.println("Enter tasks or 'q' to quit:");
        boolean quit = false;
        while (!quit) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                quit = true;
            } else {
                String[] tasks = input.split("\\s+");
                for (String str : tasks) {
                    try {
                        int task = Integer.parseInt(str);
                        taskList.addTask(task);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input: " + str);
                    }
                }
            }
        }

        for (Thread thread : calcThreads) {
            thread.interrupt();
        }
        scanner.close();
    }
}
