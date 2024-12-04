package org.example;

import java.util.Comparator;

public class MageComparator implements Comparator<Commander> {
    @Override
    public int compare(Commander m1, Commander m2){
        if (m1.getLevel() != m2.getLevel()) return Integer.compare(m1.getLevel(), m2.getLevel());
        if (m1.getName() != m2.getName()) return m1.getName().compareTo(m2.getName());
        return Double.compare(m1.getSkill(), m2.getSkill());
    }

}