package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TaskList {
    private Queue<Integer> tasks = new LinkedList<>();

    public synchronized void addTask(int task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
        notify();
    }

    public synchronized int getTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        int task = tasks.poll();
        System.out.println("Task completed: " + task);
        return task;
    }
}
