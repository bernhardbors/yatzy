package no.uib.inf101.Model.dices;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DieTest {

    @Test
    public void testDieConstructorSetsInitialValue() {
        Die die = new Die();
        int value = die.getValue();
        assertTrue(value >= 1 && value <= 6);
    }

    @Test
    public void testRollWithinRange() {
        Die die = new Die();
        for (int i = 0; i < 100; i++) {
            die.roll();
            int val = die.getValue();
            assertTrue(val >= 1 && val <= 6);
        }
    }

    @Test
    public void testHeldDieDoesNotChangeValue() {
        Die die = new Die();
        die.roll();
        int val = die.getValue();
        die.toggleHeld();
        die.roll();
        assertEquals(val, die.getValue());
    }

    @Test
    public void testToggleHeld() {
        Die die = new Die();
        assertFalse(die.isHeld());
        die.toggleHeld();
        assertTrue(die.isHeld());
        die.toggleHeld();
        assertFalse(die.isHeld());
    }
}
