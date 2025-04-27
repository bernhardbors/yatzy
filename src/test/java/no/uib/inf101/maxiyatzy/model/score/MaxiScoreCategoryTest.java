package no.uib.inf101.maxiyatzy.model.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MaxiScoreCategoryTest {
    @Test
    public void testEnumHas20Categories() {
        assertEquals(20, MaxiScoreCategory.values().length);
    }

    @Test
    public void testUpperCategories() {
        for (MaxiScoreCategory category : MaxiScoreCategory.values()) {
            switch (category) {
                case ONES, TWOS, THREES, FOURS, FIVES, SIXES -> assertTrue(category.isUpperCategory());
                default -> assertFalse(category.isUpperCategory());
            }
        }
    }

    @Test
    public void testDisplayNames() {
        assertEquals("Enere", MaxiScoreCategory.ONES.getDisplayName());
        assertEquals("Toere", MaxiScoreCategory.TWOS.getDisplayName());
        assertEquals("Treere", MaxiScoreCategory.THREES.getDisplayName());
        assertEquals("Firere", MaxiScoreCategory.FOURS.getDisplayName());
        assertEquals("Femmere", MaxiScoreCategory.FIVES.getDisplayName());
        assertEquals("Seksere", MaxiScoreCategory.SIXES.getDisplayName());
        assertEquals("Ett par", MaxiScoreCategory.ONE_PAIR.getDisplayName());
        assertEquals("To par", MaxiScoreCategory.TWO_PAIRS.getDisplayName());
        assertEquals("Tre par", MaxiScoreCategory.THREE_PAIRS.getDisplayName());
        assertEquals("Tre like", MaxiScoreCategory.THREE_OF_A_KIND.getDisplayName());
        assertEquals("Fire like", MaxiScoreCategory.FOUR_OF_A_KIND.getDisplayName());
        assertEquals("Fem like", MaxiScoreCategory.FIVE_OF_A_KIND.getDisplayName());
        assertEquals("Liten straight", MaxiScoreCategory.SMALL_STRAIGHT.getDisplayName());
        assertEquals("Stor straight", MaxiScoreCategory.LARGE_STRAIGHT.getDisplayName());
        assertEquals("Full straight", MaxiScoreCategory.FULL_STRAIGHT.getDisplayName());
        assertEquals("Hytte (2+3)", MaxiScoreCategory.FULL_HOUSE.getDisplayName());
        assertEquals("Hus (3+3)", MaxiScoreCategory.TWO_TRIPS.getDisplayName());
        assertEquals("Tårn (4+2)", MaxiScoreCategory.TOWER.getDisplayName());
        assertEquals("Sjanse", MaxiScoreCategory.CHANCE.getDisplayName());
        assertEquals("Maxi Yatzy", MaxiScoreCategory.MAXI_YATZY.getDisplayName());
    }
}
