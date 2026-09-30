package no.uib.inf101.yatzy.Model.dices;

/**
 * A factory that creates standard random dice.
 */
public class RandomDieFactory implements DieFactory {
    @Override
    public IDie getNewDie() {
        return new Die();
    }
}
