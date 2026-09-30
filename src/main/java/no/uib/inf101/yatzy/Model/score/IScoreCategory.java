package no.uib.inf101.yatzy.Model.score;

/**
 * Interface for score categories (Yatzy and MaxiYatzy).
 */
public interface IScoreCategory {

    /**
     * Returns the name of the score category.
     * 
     * @return the name of the category
     */
    String name();

    /**
     * Returns the display name of the score category, for showing to users.
     *
     * @return a user-friendly display name
     */
    default String getDisplayName() {
        return name();
    }

    /**
     * Indicates whether this category belongs to the upper section
     * (ones to sixes).
     * 
     * @return true if upper section, otherwise false
     */
    default boolean isUpperCategory() {
        return false;
    }
}
