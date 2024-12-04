package org.example;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Commander implements Comparable<Commander>{
    private String name;
    private int level;
    private double skill;

    private Set<Commander> subordinates;

    public Commander(String n, int l, double p){
        this.name = n;
        this.level = l;
        this.skill = p;
        this.subordinates = new TreeSet<>();
        //this.apprentices = a;
    }
    public String getName(){
        return name;
    }
    public int getLevel() {
        return level;
    }
    public double getSkill(){
        return skill;
    }
    public Set<Commander> getSubordinates(){
        return subordinates;
    }

    @Override
    public String toString(){
        return "Commander{name='" + name + "', level=" + level + ", skill=" + skill + "}";
    }

    public void printApprentices(int level) {
        String prefix = "-";
        for (Commander apprentice : subordinates) {
            System.out.println(prefix.repeat(level) + apprentice.toString());
            apprentice.printApprentices(level + 1);
        }
    }

    public int countApprentices(){
        int count = 0;
        for (Commander apprentice : subordinates) {
            count++;
            count += apprentice.countApprentices();
        }
        return count;
    }

    @Override
    public boolean equals(final Object other){
        if (other instanceof Commander) {
            Commander tmp = (Commander) other;
            return Objects.equals(tmp.name, this.name) && tmp.level==this.level
                    && tmp.skill ==this.skill && Objects.equals(tmp.subordinates, this.subordinates);
        }
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, level, skill, subordinates);
    }

    @Override
    public int compareTo(Commander other){
        if (this.name!=other.name) return this.name.compareTo(other.name);
        if (this.level!=other.level) return Integer.compare(this.level, other.level);
        return Double.compare(this.skill, other.skill);
    }


}
