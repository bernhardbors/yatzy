package no.uib.inf101.yatzy.maxiyatzy.model.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MaxiScoreCardTest {
    @Test
    public void testSetAndGetScore() {
        MaxiScoreCard card = new MaxiScoreCard();
        card.setScore(MaxiScoreCategory.THREE_PAIRS, 18);
        assertEquals(18, card.getScore(MaxiScoreCategory.THREE_PAIRS));
    }

    @Test
    public void testCategoryUsed() {
        MaxiScoreCard card = new MaxiScoreCard();
        assertFalse(card.isCategoryUsed(MaxiScoreCategory.FULL_STRAIGHT));
        card.setScore(MaxiScoreCategory.FULL_STRAIGHT, 21);
        assertTrue(card.isCategoryUsed(MaxiScoreCategory.FULL_STRAIGHT));
    }

    @Test
    public void testBonusAt84() {
        MaxiScoreCard card = new MaxiScoreCard();
        card.setScore(MaxiScoreCategory.ONES, 4);
        card.setScore(MaxiScoreCategory.TWOS, 8);
        card.setScore(MaxiScoreCategory.THREES, 12);
        card.setScore(MaxiScoreCategory.FOURS, 16);  
        card.setScore(MaxiScoreCategory.FIVES, 20); 
        card.setScore(MaxiScoreCategory.SIXES, 24);  

        assertEquals(84, card.getUpperSectionScore());
        assertEquals(50, card.getBonusScore());
    }

    @Test
    public void testSetScoreIgnoredIfAlreadyUsed() {
        MaxiScoreCard card = new MaxiScoreCard();
        card.setScore(MaxiScoreCategory.FIVE_OF_A_KIND, 25);
        card.setScore(MaxiScoreCategory.FIVE_OF_A_KIND, 0);
        assertEquals(25, card.getScore(MaxiScoreCategory.FIVE_OF_A_KIND));
    }

    @Test
    public void testIsFinished() {
        MaxiScoreCard card = new MaxiScoreCard();
        for (MaxiScoreCategory cat : MaxiScoreCategory.values()) {
            card.setScore(cat, 1);
        }
        assertTrue(card.isFinished());
    }
}
