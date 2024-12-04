import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Sprawdzamy, czy podano parametr startowy
        if (args.length == 0) {
            System.out.println("Missing start parameter. " +
                    "Available modes: natural, alternative, none");
            return;
        }
        Set<Mage> mageSet;

        switch (args[0]) {
            case "natural":
                mageSet = new TreeSet<>();
                break;
            case "alternative":
                mageSet = new TreeSet<>(new MageComparator());
                break;
            case "none":
                mageSet = new HashSet<>();
                break;
            default:
                System.out.println("invalid start parameter. " +
                        "Available modes: natural, alternative, none");
                return;
        }

        Mage mage1 = new Mage("Harry", 10, 15.0);
        Mage mage2 = new Mage("Ron", 7, 12.0);
        Mage mage3 = new Mage("Hermiona", 8, 12.0);
        Mage mage4 = new Mage("Luna", 6, 7.0);
        Mage mage5 = new Mage("Ginny", 6, 8.0);
        Mage mage6 = new Mage("Neville", 5, 7.0);
        Mage mage7 = new Mage("Draco", 7, 14.0);
        Mage mage8 = new Mage("Sirius", 9, 15.0);
        Mage mage9 = new Mage("Remus", 9, 14.0);
        Mage mage10 = new Mage("Dumbledore", 12, 15.0);
        Mage mage10a = new Mage("Dumbledore", 12, 15.0);

        mageSet.add(mage1);
        mageSet.add(mage2);
        mageSet.add(mage3);
        mageSet.add(mage4);
        mageSet.add(mage5);
        mageSet.add(mage6);
        mageSet.add(mage7);
        mageSet.add(mage8);
        mageSet.add(mage9);
        mageSet.add(mage10);

        mage1.getApprentices().add(mage2);
        mage1.getApprentices().add(mage3);
        mage1.getApprentices().add(mage4);

        mage2.getApprentices().add(mage5);
        mage3.getApprentices().add(mage6);

        System.out.println("mages: ");
        for (Mage mage : mageSet) {
            System.out.println(mage);
        }

        Map<Mage, Integer> apprenticesStatistics = generateApprenticesStatistics(mageSet);

        System.out.println("statistics: ");
        for (Map.Entry<Mage, Integer> entry : apprenticesStatistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " apprentices");
        }

        System.out.println(mage10.equals(mage10a));
        System.out.println(mage10.equals(mage9));

        mage1.printApprentices(1);
        System.out.println(mage1.countApprentices());

    }

    public static Map<Mage, Integer> generateApprenticesStatistics(Set<Mage> mages) {
        Map<Mage, Integer> apprenticesStatistics = new HashMap<>();
        for (Mage mage : mages) {
            int apprenticesCount = mage.countApprentices();
            apprenticesStatistics.put(mage, apprenticesCount);
        }
        return apprenticesStatistics;
    }

/*    public static int countApprentices(Mage mage) {
        Set<Mage> visited = new HashSet<>();
        return countApprenticesRecursive(mage, visited);
    }

    private static int countApprenticesRecursive(Mage mage, Set<Mage> visited) {
        visited.add(mage);
        int count = mage.getApprentices().size();
        for (Mage apprentice : mage.getApprentices()) {
            if (!visited.contains(apprentice)) {
                count += countApprenticesRecursive(apprentice, visited);
            }
        }
        return count;
    }*/
}