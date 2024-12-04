import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;

public class Mage implements Comparable<Mage>{
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String n, int l, double p){
        this.name = n;
        this.level = l;
        this.power = p;
        this.apprentices = new TreeSet<>();
        //this.apprentices = a;
    }
    public String getName(){
        return name;
    }
    public int getLevel() {
        return level;
    }
    public double getPower(){
        return power;
    }
    public Set<Mage> getApprentices(){
        return apprentices;
    }

    @Override
    public String toString(){
        return "Mage{name='" + name + "', level=" + level + ", power=" + power + "}";
    }

    public void printApprentices(int level) {
        String prefix = "-";
        for (Mage apprentice : apprentices) {
            System.out.println(prefix.repeat(level) + apprentice.toString());
            apprentice.printApprentices(level + 1);
        }
    }

    public int countApprentices(){
        int count = 0;
        for (Mage apprentice : apprentices) {
            count++;
            count += apprentice.countApprentices();
        }
        return count;
    }

    @Override
    public boolean equals(final Object other){
        if (other instanceof Mage) {
            Mage tmp = (Mage) other;
            return Objects.equals(tmp.name, this.name) && tmp.level==this.level
                    && tmp.power==this.power && Objects.equals(tmp.apprentices, this.apprentices);
        }
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, level, power, apprentices);
    }

    @Override
    public int compareTo(Mage other){
        if (this.name!=other.name) return this.name.compareTo(other.name);
        if (this.level!=other.level) return Integer.compare(this.level, other.level);
        return Double.compare(this.power, other.power);
    }


}
