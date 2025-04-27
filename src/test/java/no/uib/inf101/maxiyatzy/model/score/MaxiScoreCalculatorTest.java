package no.uib.inf101.maxiyatzy.model.score;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.Model.score.IScoreCalculator;

public class MaxiScoreCalculatorTest {

    private final IScoreCalculator calculator = new MaxiScoreCalculator();

    @Test
    public void testThreePairs() {
        int[] dice = {1, 1, 2, 2, 3, 3};
        assertEquals(12, calculator.calculateScore(dice, MaxiScoreCategory.THREE_PAIRS));
        int[] dice2 = {1, 1, 1, 1, 2, 2};
        assertEquals(8, calculator.calculateScore(dice2, MaxiScoreCategory.THREE_PAIRS));
        int[] dice3 = {1, 1, 1, 1, 1, 1};
        assertEquals(6, calculator.calculateScore(dice3, MaxiScoreCategory.THREE_PAIRS));
    }

    @Test
    public void testFiveOfAKind() {
        int[] dice = {5, 5, 5, 5, 5, 2};
        assertEquals(25, calculator.calculateScore(dice, MaxiScoreCategory.FIVE_OF_A_KIND));
    }

    @Test
    public void testFullStraight() {
        int[] dice = {1, 2, 3, 4, 5, 6};
        assertEquals(21, calculator.calculateScore(dice, MaxiScoreCategory.FULL_STRAIGHT));
        int[] dice2 = {1, 1, 1, 1, 2, 2};
        assertEquals(0, calculator.calculateScore(dice2, MaxiScoreCategory.FULL_STRAIGHT));

    }

    @Test
    public void testTwoTrips() {
        int[] dice = {6, 6, 6, 3, 3, 3};
        assertEquals(27, calculator.calculateScore(dice, MaxiScoreCategory.TWO_TRIPS));
        int[] dice2 = {6, 6, 6, 6, 6, 6};
        assertEquals(36, calculator.calculateScore(dice2, MaxiScoreCategory.TWO_TRIPS));
        int[] dice3 = {1, 1, 1, 1, 2, 2};
        assertEquals(0, calculator.calculateScore(dice3, MaxiScoreCategory.TWO_TRIPS));
    }

    @Test
    public void testTower() {
        int[] dice = {4, 4, 4, 4, 2, 2};
        assertEquals(20, calculator.calculateScore(dice, MaxiScoreCategory.TOWER));
        int [] dice2 = {4, 4, 4, 4, 4, 4};
        assertEquals(24, calculator.calculateScore(dice2, MaxiScoreCategory.TOWER));
        int[] dice3 = {1, 1, 1, 2, 2, 2};
        assertEquals(0, calculator.calculateScore(dice3, MaxiScoreCategory.TOWER));
    }

    @Test
    public void testFallbackToRegularCategory() {
        int[] dice = {1, 1, 2, 3, 4, 5};
        int score = calculator.calculateScore(dice, MaxiScoreCategory.ONE_PAIR);
        assertEquals(2, score);
    }

    @Test
    public void testMaxiYatzy() {
        int[] dice = {1, 2, 3, 4, 5, 6};
        assertEquals(0, calculator.calculateScore(dice, MaxiScoreCategory.MAXI_YATZY));
        int[] dice2 = {1, 1, 1, 1, 1, 1};
        assertEquals(100, calculator.calculateScore(dice2, MaxiScoreCategory.MAXI_YATZY));
    }
}
