package no.uib.inf101.Model.score;

import java.util.EnumMap;

/**
 * Default implementation of IScoreCard using an EnumMap.
 * Supports any enum that implements IScoreCategory.
 */
public class ScoreCard<T extends Enum<T> & IScoreCategory> implements IScoreCard {
    protected final EnumMap<T, Integer> scores;
    protected final T[] allCategories;

    /**
     * Constructs a ScoreCard with all possible categories.
     *
     * @param allCategories array of all possible categories (must be complete)
     */
    public ScoreCard(T[] allCategories) {
        this.allCategories = allCategories;
        this.scores = new EnumMap<>(allCategories[0].getDeclaringClass());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isCategoryUsed(IScoreCategory category) {
        return scores.containsKey((T) category);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setScore(IScoreCategory category, int score) {
        scores.putIfAbsent((T) category, score);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getScore(IScoreCategory category) {
        return scores.getOrDefault((T) category, 0);
    }

    @Override
    public int getTotalScore() {
        return scores.values().stream().mapToInt(Integer::intValue).sum() + getBonusScore();
    }

    @Override
    public boolean isFinished() {
        return scores.size() == allCategories.length;
    }

    @Override
    public int getUpperSectionScore() {
        int sum = 0;
        for (T cat : allCategories) {
            if (cat.isUpperCategory()) {
                sum += getScore(cat);
            }
        }
        return sum;
    }

    @Override
    public int getBonusScore() {
        int upperTotal = getUpperSectionScore();

        if (upperTotal >= 63) {
            return 50;
        }
        return 0;
    }
}
