package no.uib.inf101.yatzy.Model.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreCardTest {
    @Test
    public void testSetAndGetScore() {
        ScoreCard<ScoreCategory> card = new ScoreCard<>(ScoreCategory.values());
        card.setScore(ScoreCategory.TWOS, 8);
        assertEquals(8, card.getScore(ScoreCategory.TWOS));
    }

    @Test
    public void testCategoryUsed() {
        ScoreCard<ScoreCategory> card = new ScoreCard<>(ScoreCategory.values());
        assertFalse(card.isCategoryUsed(ScoreCategory.FOURS));
        card.setScore(ScoreCategory.FOURS, 12);
        assertTrue(card.isCategoryUsed(ScoreCategory.FOURS));
    }

    @Test
    public void testTotalScore() {
        ScoreCard<ScoreCategory> card = new ScoreCard<>(ScoreCategory.values());
        card.setScore(ScoreCategory.ONES, 3);
        card.setScore(ScoreCategory.FIVES, 10);
        assertEquals(13, card.getTotalScore());
    }

    @Test
    public void testBonusGiven() {
        ScoreCard<ScoreCategory> card = new ScoreCard<>(ScoreCategory.values());
        card.setScore(ScoreCategory.ONES, 3);
        card.setScore(ScoreCategory.TWOS, 6);
        card.setScore(ScoreCategory.THREES, 9);
        card.setScore(ScoreCategory.FOURS, 12);
        card.setScore(ScoreCategory.FIVES, 15);
        card.setScore(ScoreCategory.SIXES, 18);
        assertEquals(63, card.getUpperSectionScore());
        assertEquals(50, card.getBonusScore());
    }

    @Test
    public void testIsFinished() {
        ScoreCard<ScoreCategory> card = new ScoreCard<>(ScoreCategory.values());
        for (ScoreCategory cat : ScoreCategory.values()) {
            card.setScore(cat, 1);
        }
        assertTrue(card.isFinished());
    }

    @Test
    public void testSetScoreIgnoredIfAlreadyUsed() {
        ScoreCard<ScoreCategory> card = new ScoreCard<>(ScoreCategory.values());
        card.setScore(ScoreCategory.TWOS, 8);
        card.setScore(ScoreCategory.TWOS, 4);
        assertEquals(8, card.getScore(ScoreCategory.TWOS));
    }
}
