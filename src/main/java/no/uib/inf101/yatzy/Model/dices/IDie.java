package no.uib.inf101.yatzy.Model.dices;

/**
 * Represents a single die with a value from 1 to 6 and a held state.
 */
public interface IDie {

    /**
     * Returns the current face of this die (1-6).
     * 
     * @return the current value of the die
     */
    int getValue();

    /**
     * Returns whether this die is currently held and cannot be rolled.
     * 
     * @return true if the die is held, false otherwise
     */
    boolean isHeld();

    /**
     * Rolls the die, changing its value to a random number between 1 and 6.
     */
    void roll();

    /**
     * Toggles the held state of the die. If the die is held, it will be released,
     * and vice versa.
     */
    void toggleHeld();

    /**
     * Releases the die so it is no longer held.
     */
    void release();
}
