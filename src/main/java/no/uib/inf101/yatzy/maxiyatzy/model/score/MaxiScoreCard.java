package no.uib.inf101.yatzy.maxiyatzy.model.score;

import no.uib.inf101.yatzy.Model.score.IScoreCategory;
import no.uib.inf101.yatzy.Model.score.ScoreCard;

/**
 * Scorecard for MaxiYatzy.
 * Supports extended categories and custom bonus rules.
 */
public class MaxiScoreCard extends ScoreCard<MaxiScoreCategory> {

    /**
     * Constructs a new MaxiScoreCard using the MaxiScoreCategory enum.
     */
    public MaxiScoreCard() {
        super(MaxiScoreCategory.values());
    }

    @Override
    public int getBonusScore() {
        int upper = getUpperSectionScore();
        return upper >= 84 ? 50 : 0;
    }

    @Override
    public boolean isFinished() {
        return getUsedCategoriesCount() == MaxiScoreCategory.values().length;
    }

    private int getUsedCategoriesCount() {
        int count = 0;
        for (IScoreCategory c : MaxiScoreCategory.values()) {
            if (isCategoryUsed(c)) {
                count++;
            }
        }
        return count;
    }
}
