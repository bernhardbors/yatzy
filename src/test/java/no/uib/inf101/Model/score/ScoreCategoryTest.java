package no.uib.inf101.Model.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreCategoryTest {

    @Test
    public void testEnumHas15Categories() {
        assertEquals(15, ScoreCategory.values().length);
    }

    @Test
    public void testUpperCategories() {
        assertTrue(ScoreCategory.ONES.isUpperCategory());
        assertTrue(ScoreCategory.TWOS.isUpperCategory());
        assertTrue(ScoreCategory.THREES.isUpperCategory());
        assertTrue(ScoreCategory.FOURS.isUpperCategory());
        assertTrue(ScoreCategory.FIVES.isUpperCategory());
        assertTrue(ScoreCategory.SIXES.isUpperCategory());
        assertFalse(ScoreCategory.FULL_HOUSE.isUpperCategory());
    }

    @Test
    public void testDisplayNames() {
        assertEquals("Enere", ScoreCategory.ONES.getDisplayName());
        assertEquals("Toere", ScoreCategory.TWOS.getDisplayName());
        assertEquals("Treere", ScoreCategory.THREES.getDisplayName());
        assertEquals("Firere", ScoreCategory.FOURS.getDisplayName());
        assertEquals("Femmere", ScoreCategory.FIVES.getDisplayName());
        assertEquals("Seksere", ScoreCategory.SIXES.getDisplayName());
        assertEquals("Ett par", ScoreCategory.ONE_PAIR.getDisplayName());
        assertEquals("To par", ScoreCategory.TWO_PAIRS.getDisplayName());
        assertEquals("Tre like", ScoreCategory.THREE_OF_A_KIND.getDisplayName());
        assertEquals("Fire like", ScoreCategory.FOUR_OF_A_KIND.getDisplayName());
        assertEquals("Liten straight", ScoreCategory.SMALL_STRAIGHT.getDisplayName());
        assertEquals("Stor straight", ScoreCategory.LARGE_STRAIGHT.getDisplayName());
        assertEquals("Hus (2+3)", ScoreCategory.FULL_HOUSE.getDisplayName());
        assertEquals("Sjanse", ScoreCategory.CHANCE.getDisplayName());
        assertEquals("Yatzy", ScoreCategory.YATZY.getDisplayName());
    }
}
