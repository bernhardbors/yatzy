package no.uib.inf101.yatzy.Model.score;

/**
 * Represents a player's scorecard in Yatzy, which tracks
 * used categories and associated scores.
 */
public interface IScoreCard {

    /**
     * Checks if a score category has already been used.
     * 
     * @param category the score category to check
     * @return true if the category has been used, false otherwise
     */
    boolean isCategoryUsed(IScoreCategory category);

    /**
     * Assigns a score to the given category if it is unused.
     *
     * @param category the score category to set
     * @param score    the score to assign
     */
    void setScore(IScoreCategory category, int score);

    /**
     * Returns the score for a given category.
     *
     * @param category the score category to query
     * @return the score for the category, or 0 if unused
     */
    int getScore(IScoreCategory category);

    /**
     * Calculates the sum of ONES through SIXES.
     *
     * @return upper section score
     */
    int getUpperSectionScore();

    /**
     * Calculates bonus points if upper section score is 63 or more.
     *
     * @return 50 if bonus is earned, otherwise 0
     */
    int getBonusScore();

    /**
     * Calculates and returns the total score.
     *
     * @return the sum of all recorded scores
     */
    int getTotalScore();

    /**
     * Checks whether all categories have been filled in.
     *
     * @return true if the scorecard is complete, false otherwise
     */
    boolean isFinished();

}
