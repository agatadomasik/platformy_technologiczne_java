package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing startup item. " +
                    "Available modes: natural, alternative, none");
            return;
        }
        Set<Commander> commanderSet;

        switch (args[0]) {
            case "natural":
                commanderSet = new TreeSet<>();
                break;
            case "alternative":
                commanderSet = new TreeSet<>(new MageComparator());
                break;
            case "none":
                commanderSet = new HashSet<>();
                break;
            default:
                System.out.println("Invalid startup item. " +
                        "Available modes: natural, alternative, none");
                return;
        }

        Commander commander1 = new Commander("Harry", 10, 15.0);
        Commander commander2 = new Commander("Ron", 7, 12.0);
        Commander commander3 = new Commander("Hermiona", 8, 12.0);
        Commander commander4 = new Commander("Luna", 6, 7.0);
        Commander commander5 = new Commander("Ginny", 6, 8.0);
        Commander commander6 = new Commander("Neville", 5, 7.0);
        Commander commander7 = new Commander("Draco", 7, 14.0);
        Commander commander8 = new Commander("Sirius", 9, 15.0);
        Commander commander9 = new Commander("Remus", 9, 14.0);
        Commander commander10 = new Commander("Dumbledore", 12, 15.0);
        Commander commander10A = new Commander("Dumbledore", 12, 15.0);

        commanderSet.add(commander1);
        commanderSet.add(commander2);
        commanderSet.add(commander3);
        commanderSet.add(commander4);
        commanderSet.add(commander5);
        commanderSet.add(commander6);
        commanderSet.add(commander7);
        commanderSet.add(commander8);
        commanderSet.add(commander9);
        commanderSet.add(commander10);

        commander1.getSubordinates().add(commander2);
        commander1.getSubordinates().add(commander3);
        commander1.getSubordinates().add(commander4);

        commander2.getSubordinates().add(commander5);
        commander3.getSubordinates().add(commander6);

        commander5.getSubordinates().add(commander7);
        System.out.println("Commanders: ");
        for (Commander commander : commanderSet) {
            System.out.println(commander);
            commander.printApprentices(1);
        }

        Map<Commander, Integer> apprenticesStatistics = generateApprenticesStatistics(commanderSet, args[0]);

        System.out.println("Apprentices statistics: ");
        for (Map.Entry<Commander, Integer> stat : apprenticesStatistics.entrySet()) {
            System.out.println(stat.getKey() + ": " + stat.getValue() + " apprentices");
        }

        //System.out.println(mage10.equals(mage10a));
        //System.out.println(mage10.equals(mage9));

        //mage1.printApprentices(1);
        //System.out.println(mage1.countApprentices());
    }

    public static Map<Commander, Integer> generateApprenticesStatistics(Set<Commander> commanders, String mode) {
        Map<Commander, Integer> stats;

        switch (mode) {
            case "natural":
                stats = new TreeMap<>();
                break;
            case "alternative":
                stats = new TreeMap<>(new MageComparator());
                break;
            case "none":
                stats = new HashMap<>();
                break;
            default:
                throw new IllegalArgumentException("Invalid parameter: " + mode);
        }

        for (Commander commander : commanders) {
            stats.put(commander, commander.countApprentices());
        }
        return stats;
    }
}


