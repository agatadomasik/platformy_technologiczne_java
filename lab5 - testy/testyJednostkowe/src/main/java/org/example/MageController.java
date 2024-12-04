package org.example;

import java.util.Optional;


public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> optionalMage = repository.find(name);
        if (optionalMage.isPresent()) {
            Mage mage = optionalMage.get();
            return mage.getName() + " " + mage.getLevel();
        } else {
            return "not found";
        }
    }

    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }

    public String save(String name, String level) {
        try {
            int lvl = Integer.parseInt(level);
            Mage mage = new Mage(name, lvl);
            repository.save(mage);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }
}

