package no.uib.inf101.Model.dices;

/**
 * Interface for a set of dice.
 */
public interface IDiceSet {

    /**
     * Rolls all unheld dice in the set.
     */
    void rollAll();

    /**
     * Releases all dice so they kan be rolled again.
     */
    void releaseAll();

    /**
     * Returns the die at the given index.
     * 
     * @param index the index of the die to retrieve (0–4)
     * @return the die at the specified index
     * @throws ArrayIndexOutOfBoundsException if index is invalid
     */
    IDie getDie(int index);

    /**
     * Returns an array of all dice in this set.
     * 
     * @return an array containing all dice
     */
    IDie[] getAllDice();
}
