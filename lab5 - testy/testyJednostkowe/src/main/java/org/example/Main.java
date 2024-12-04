package org.example;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        MageRepository repository = new MageRepository();
        MageController controller = new MageController(repository);

        // CONTROLLER
        // SAVE
        System.out.println("controller testing: ");
        System.out.println(controller.save("mage1", "20"));
        System.out.println(controller.save("mage2", "25"));
        System.out.println(controller.save("mage2", "25"));

        // FIND
        System.out.println(controller.find("mage1"));
        System.out.println(controller.find("mage2"));
        System.out.println(controller.find("mage3"));

        // DELETE
        System.out.println(controller.delete("mage1"));
        System.out.println(controller.find("mage1"));
        System.out.println(controller.delete("mage2"));
        System.out.println(controller.delete("mage3"));

        // REPOSITORY
        System.out.println("repository testing: ");
        Mage mage4 = new Mage("mage4", 20);

        // SAVE
        repository.save(mage4);
        // repository.save(mage4);

        // FIND
        Optional<Mage> mageOptional1 = repository.find("mage4");
        Optional<Mage> mageOptional2 = repository.find("mage5");

        // DELETE
        repository.delete(mage4.getName());
        //repository.delete(mage4.getName());

    }
}
