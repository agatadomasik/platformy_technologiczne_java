package org.example;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class Mage {
    private String name;
    private int level;

    public Mage(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    @Override
    public String toString(){
        return "Mage{name='" + name + "', level=" + level + "}";
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, level);
    }

    @Override
    public boolean equals(final Object other){
        if (other instanceof Mage) {
            Mage tmp = (Mage) other;
            return Objects.equals(tmp.name, this.name) && tmp.level==this.level;
        }
        else return false;
    }
}
