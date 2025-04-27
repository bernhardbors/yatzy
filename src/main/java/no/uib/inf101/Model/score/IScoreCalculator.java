package no.uib.inf101.Model.score;

public interface IScoreCalculator {

    /**
     * Calculates the score for a set of dice in the given category.
     * 
     * @param dice     an array of integers representing the dice values (1-6)
     * @param category the score category to calculate for
     * @return the calculated score for the category
     * @throws IllegalArgumentException if the dice array is null or invalid
     * 
     */
    int calculateScore(int[] dice, IScoreCategory category);
}
