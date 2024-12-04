package org.example;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MageControllerTest {
    private MageRepository repository;
    private MageController controller;

    @BeforeEach
    void setUp() {
        repository = mock(MageRepository.class);
        controller = new MageController(repository);
    }

    @Test
    void testFindMageExists() {
        Mage mage = new Mage("Gandalf", 20);
        when(repository.find("Gandalf")).thenReturn(Optional.of(mage));
        String result = controller.find("Gandalf");
        assertEquals("Gandalf 20", result);
    }

    @Test
    void testFindMageDoesNotExist() {
        when(repository.find("Merlin")).thenReturn(Optional.empty());
        String result = controller.find("Merlin");
        assertEquals("not found", result);
    }

    @Test
    void testDeleteMageExists() {
        String result = controller.delete("Gandalf");
        assertEquals("done", result);
        verify(repository, times(1)).delete("Gandalf");
    }

    @Test
    void testDeleteMageDoesNotExist() {
/*        String result = controller.delete("Merlin");
        assertEquals("not found", result);
        verify(repository, times(1)).delete("Merlin");*/
        doThrow(new IllegalArgumentException("not found")).when(repository).delete("xd");
        String result = controller.delete("xd");
        assertEquals("not found", result);
    }

    @Test
    void testSaveMageDoesNotExist() {
        String result = controller.save("Gandalf", "20");
        assertEquals("done", result);
        verify(repository, times(1)).save(any(Mage.class));
    }

    @Test
    void testSaveMageAlreadyExists() {
        Mage mage1 = new Mage("Gandalf", 20);
        Mage mage2 = new Mage("Gandalf", 20);
        repository.save(mage1);
        doThrow(new IllegalArgumentException("bad request")).when(repository).save(mage1);
        String result = controller.save("Gandalf", "20");
        assertEquals("bad request", result);
        //verify(repository, never()).save(any(Mage.class));
    }


}

