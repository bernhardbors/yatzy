package no.uib.inf101.Model.dices;

/**
 * Interface for creating dice.
 */
public interface DieFactory {

    /**
     * Creates and returns a new die.
     *
     * @return a new IDie instance
     */
    IDie getNewDie();
}
