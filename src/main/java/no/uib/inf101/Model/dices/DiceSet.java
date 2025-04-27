package no.uib.inf101.Model.dices;

/**
 * Represents a set of dice in a Yatzy game.
 * Can support standard or MaxiYatzy with customizable number of dice.
 */
public class DiceSet implements IDiceSet {
    private final DieFactory dieFactory;
    private final IDie[] dice;

    /**
     * Constructs a new DiceSet with the specified number of dice.
     *
     * @param count the number of dice to include in the set
     * @param factory the factory for creating dice
     */
    public DiceSet(int count, DieFactory factory) {
        this.dieFactory = factory;
        dice = new IDie[count];
        for (int i = 0; i < count; i++) {
            dice[i] = dieFactory.getNewDie();
        }
    }

    @Override
    public void rollAll() {
        for (IDie die : dice) {
            die.roll();
        }
    }

    @Override
    public IDie getDie(int index) {
        return dice[index];
    }

    @Override
    public IDie[] getAllDice() {
        return dice;
    }

    @Override
    public void releaseAll() {
        for (IDie die: dice) {
            die.release();
        }
    }
}
