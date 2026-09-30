package no.uib.inf101.yatzy.Model.dices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiceSetTest {

    @Test
    public void testFiveDiceCreated() {
        IDiceSet set = new DiceSet(5, new RandomDieFactory() {
            @Override
            public IDie getNewDie() {
                return new Die();
            }
        });
        assertEquals(5, set.getAllDice().length);
    }

    @Test
    public void testRollChangesDiceValues() {
        IDiceSet set = new DiceSet(5, new RandomDieFactory());
        int[] before = new int[5];
        for (int i = 0; i < 5; i++) {
            before[i] = set.getDie(i).getValue();
        }
        set.rollAll();
        boolean changed = false;
        for (int i = 0; i < 5; i++) {
            if (set.getDie(i).getValue() != before[i]) {
                changed = true;
                break;
            }
        }
        assertTrue(changed);
    }

    @Test
    public void testReleaseAllUnholdsDice() {
        IDiceSet set = new DiceSet(5, new RandomDieFactory());
        set.getDie(0).toggleHeld();
        assertTrue(set.getDie(0).isHeld());
        set.releaseAll();
        assertFalse(set.getDie(0).isHeld());
    }
}
