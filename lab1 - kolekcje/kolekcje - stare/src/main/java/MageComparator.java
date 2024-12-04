import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage m1, Mage m2){
        if (m1.getLevel() != m2.getLevel()) return Integer.compare(m1.getLevel(), m2.getLevel());
        if (m1.getName() != m2.getName()) return m1.getName().compareTo(m2.getName());
        return Double.compare(m1.getPower(), m2.getPower());
    }

}