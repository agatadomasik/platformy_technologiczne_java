package org.example;

import java.util.ArrayList;
import java.util.List;

public class ResultList {
    private List<Integer> results = new ArrayList<>();

    public synchronized void addResult(int result) {
        results.add(result);
        System.out.println("Result added: " + result);
    }

    public synchronized List<Integer> getResults() {
        return new ArrayList<>(results);
    }
}
