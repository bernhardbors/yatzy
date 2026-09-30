package no.uib.inf101.yatzy.Model.score;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {

    private final IScoreCalculator calculator = new ScoreCalculator();

    @Test
    public void testUpperSection() {
        assertEquals(3, calculator.calculateScore(new int[] { 1, 1, 1, 2, 3 }, ScoreCategory.ONES));
        assertEquals(4, calculator.calculateScore(new int[] { 2, 2, 3, 4, 5 }, ScoreCategory.TWOS));
        assertEquals(9, calculator.calculateScore(new int[] { 3, 3, 3, 4, 5 }, ScoreCategory.THREES));
        assertEquals(8, calculator.calculateScore(new int[] { 4, 4, 1, 2, 3 }, ScoreCategory.FOURS));
        assertEquals(25, calculator.calculateScore(new int[] { 5, 5, 5, 5, 5 }, ScoreCategory.FIVES));
        assertEquals(30, calculator.calculateScore(new int[] { 6, 6, 6, 6, 6 }, ScoreCategory.SIXES));
    }

    @Test
    public void testOnePair() {
        assertEquals(12, calculator.calculateScore(new int[] { 6, 6, 2, 3, 4 }, ScoreCategory.ONE_PAIR));
        assertEquals(10, calculator.calculateScore(new int[] { 5, 5, 3, 2, 1 }, ScoreCategory.ONE_PAIR));
        assertEquals(0, calculator.calculateScore(new int[] { 1, 2, 3, 4, 5 }, ScoreCategory.ONE_PAIR));
    }

    @Test
    public void testTwoPairs() {
        assertEquals(16, calculator.calculateScore(new int[] { 6, 6, 2, 2, 3 }, ScoreCategory.TWO_PAIRS));
        assertEquals(8, calculator.calculateScore(new int[] {2,2,2,2,5}, ScoreCategory.TWO_PAIRS));
        assertEquals(0, calculator.calculateScore(new int[] { 1, 2, 3, 4, 5 }, ScoreCategory.TWO_PAIRS));
    }

    @Test
    public void testThreeOfAKind() {
        assertEquals(18, calculator.calculateScore(new int[] { 6, 6, 6, 2, 3 }, ScoreCategory.THREE_OF_A_KIND));
        assertEquals(0, calculator.calculateScore(new int[] { 1, 2, 2, 3, 4 }, ScoreCategory.THREE_OF_A_KIND));
    }

    @Test
    public void testFourOfAKind() {
        assertEquals(24, calculator.calculateScore(new int[] { 6, 6, 6, 6, 3 }, ScoreCategory.FOUR_OF_A_KIND));
        assertEquals(0, calculator.calculateScore(new int[] { 2, 2, 2, 3, 4 }, ScoreCategory.FOUR_OF_A_KIND));
    }

    @Test
    public void testFullHouse() {
        assertEquals(13, calculator.calculateScore(new int[] { 2, 2, 3, 3, 3 }, ScoreCategory.FULL_HOUSE));
        assertEquals(10, calculator.calculateScore(new int[] {2,2,2,2,2}, ScoreCategory.FULL_HOUSE));
        assertEquals(0, calculator.calculateScore(new int[] { 2, 2, 2, 2, 5 }, ScoreCategory.FULL_HOUSE));
    }

    @Test
    public void testSmallStraight() {
        assertEquals(15, calculator.calculateScore(new int[] { 1, 2, 3, 4, 5 }, ScoreCategory.SMALL_STRAIGHT));
        assertEquals(15, calculator.calculateScore(new int[] { 1, 2, 3, 4, 5, 5 }, ScoreCategory.SMALL_STRAIGHT));
        assertEquals(0, calculator.calculateScore(new int[] {1,2,4,5,5}, ScoreCategory.SMALL_STRAIGHT));
        assertEquals(0, calculator.calculateScore(new int[] { 2, 3, 4, 5, 6 }, ScoreCategory.SMALL_STRAIGHT));
    }

    @Test
    public void testLargeStraight() {
        assertEquals(20, calculator.calculateScore(new int[] { 2, 3, 4, 5, 6 }, ScoreCategory.LARGE_STRAIGHT));
        assertEquals(20, calculator.calculateScore(new int[] { 2, 3, 4, 5, 6, 6 }, ScoreCategory.LARGE_STRAIGHT));
        assertEquals(0, calculator.calculateScore(new int[] { 1, 2, 3, 4, 5 }, ScoreCategory.LARGE_STRAIGHT));
    }


    @Test
    public void testChance() {
        assertEquals(21, calculator.calculateScore(new int[] { 1, 5, 5, 6, 4 }, ScoreCategory.CHANCE));
        assertEquals(20, calculator.calculateScore(new int[] { 2, 4, 4, 5, 5 }, ScoreCategory.CHANCE));
        assertEquals(30, calculator.calculateScore(new int[] {6,6,6,6,6}, ScoreCategory.CHANCE));
    }

    @Test
    public void testYatzy() {
        assertEquals(100, calculator.calculateScore(new int[] { 6, 6, 6, 6, 6 }, ScoreCategory.YATZY));
        assertEquals(0, calculator.calculateScore(new int[] { 6, 6, 6, 6, 5 }, ScoreCategory.YATZY));
    }
}
