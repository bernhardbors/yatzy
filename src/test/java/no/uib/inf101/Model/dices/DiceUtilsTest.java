package no.uib.inf101.Model.dices;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class DiceUtilsTest {

    @Test
    void testGetValues_withFixedDice() {
        Die die1 = new Die();
        Die die2 = new Die();
        Die die3 = new Die();

        setDieValue(die1, 2);
        setDieValue(die2, 5);
        setDieValue(die3, 6);

        IDie[] dice = {die1, die2, die3};
        int[] expected = {2, 5, 6};

        assertArrayEquals(expected, DiceUtils.getValues(dice));
    }

    @Test
    void testGetValues_withEmptyArray() {
        IDie[] dice = {};
        int[] expected = {};

        assertArrayEquals(expected, DiceUtils.getValues(dice));
    }

    @Test
    void testGetValues_withSingleDie() {
        Die die = new Die();
        setDieValue(die, 4);

        IDie[] dice = {die};
        int[] expected = {4};

        assertArrayEquals(expected, DiceUtils.getValues(dice));
    }


    // Helper method to set the value of a die
    private void setDieValue(Die die, int value) {
        if (die.isHeld()) {
            die.release();
        }
        while (die.getValue() != value) {
            die.roll();
        }
    }
}

