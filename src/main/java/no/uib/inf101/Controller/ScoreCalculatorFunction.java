package no.uib.inf101.Controller;

import no.uib.inf101.Model.score.IScoreCategory;

/**
 * Functional interface for calculating score in a given category
 * based on current dice values.
 */
@FunctionalInterface
public interface ScoreCalculatorFunction {

    /**
     * Calculates a score for the given dice values and category.
     *
     * @param diceValues array of dice values (expected 5 values from 1 to 6)
     * @param category   the score category to evaluate
     * @return the calculated score
     */
    int calculate(int[] diceValues, IScoreCategory category);
}
