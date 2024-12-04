package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MageRepositoryTest {
    private MageRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MageRepository();
    }

    @Test
    void testFindWhenMageExists() {
        Mage mage = new Mage("Gandalf", 20);
        repository.save(mage);
        Optional<Mage> foundMage = repository.find("Gandalf");
        assertTrue(foundMage.isPresent());
        assertEquals("Gandalf", foundMage.get().getName());
        assertEquals(20, foundMage.get().getLevel());
    }

    @Test
    void testFindWhenMageDoesNotExist() {
        Optional<Mage> foundMage = repository.find("Merlin");
        assertFalse(foundMage.isPresent());
    }

    @Test
    void testDeleteWhenMageExists() {
        Mage mage = new Mage("Gandalf", 20);
        repository.save(mage);
        repository.delete("Gandalf");
        Optional<Mage> deletedMage = repository.find("Gandalf");
        assertFalse(deletedMage.isPresent());
    }

    @Test
    void testDeleteWhenMageDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            repository.delete("Merlin");
        });
    }

    @Test
    void testSaveWhenMageDoesNotExist() {
        Mage mage = new Mage("Gandalf", 20);
        repository.save(mage);
        Optional<Mage> savedMage = repository.find("Gandalf");
        assertTrue(savedMage.isPresent());
        assertEquals("Gandalf", savedMage.get().getName());
        assertEquals(20, savedMage.get().getLevel());
    }

    @Test
    void testSaveWhenMageAlreadyExists() {
        Mage mage = new Mage("Gandalf", 20);
        repository.save(mage);
        assertThrows(IllegalArgumentException.class, () -> {
            repository.save(mage);
        });
    }
}

