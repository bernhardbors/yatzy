package no.uib.inf101.yatzy.Model.dices;

import java.util.Random;

/**
 * A single six-sided die that can be rolled and held.
 */
public class Die implements IDie {
    private int value;
    private boolean held;
    private static final Random random = new Random();

    /**
     * Constructs a new die that can be rolled and held.
     */
    public Die() {
        roll();
    }

    @Override
    public void roll() {
        if (!held) {
            value = random.nextInt(6) + 1;
        }
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isHeld() {
        return held;
    }

    @Override
    public void toggleHeld() {
        held = !held;
    }

    @Override
    public void release() {
        held = false;
    }
}
