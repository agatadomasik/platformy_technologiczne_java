package org.example;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Mage {
    @Id
    private String name;
    private int level;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Tower tower;

    public Mage(){}

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
    }

    // Getters and setters
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level &&
                Objects.equals(name, mage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
    @Override
    public String toString(){
        return "Mage{name='" + name + "', level=" + level + ", tower=" + tower.getName() + "}";
    }

}
