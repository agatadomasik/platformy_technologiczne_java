package org.example;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tower {
    @Id
    private String name;
    private int height;
    @OneToMany(mappedBy = "tower")
    private List<Mage> mages;

    public Tower(){}

    public Tower(String name, int height){
        this.name = name;
        this.height = height;
        this.mages = new ArrayList<>();
    }
    String getName() {
        return this.name;
    }
    int getHeight(){
        return this.height;
    }

    @Override
    public String toString(){
        return "Tower{name='" + name + "', height=" + height + "}";
    }

    public List<Mage> getMages() {
        return mages;
    }
}
