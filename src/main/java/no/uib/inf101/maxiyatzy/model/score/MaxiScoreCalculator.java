package no.uib.inf101.maxiyatzy.model.score;

import no.uib.inf101.Model.score.IScoreCalculator;
import no.uib.inf101.Model.score.IScoreCategory;
import no.uib.inf101.Model.score.ScoreCalculator;
import no.uib.inf101.Model.score.ScoreCategory;

import static no.uib.inf101.Model.dices.DiceUtils.countDice;

/**
 * Provides custom score calculations for the MaxiYatzy variant.
 * Implements the base IScoreCalculator for shared logic.
 */
public class MaxiScoreCalculator implements IScoreCalculator {

    @Override
    public int calculateScore(int[] dice, IScoreCategory category) {
        if (!(category instanceof MaxiScoreCategory maxicategory)) {
            throw new IllegalArgumentException("Invalid category type for MaxiScoreCalculator");
        }

        int[] counts = countDice(dice);

        switch (maxicategory) {
            case THREE_PAIRS -> {
                for (int i = 1; i <= 6; i++) {
                    if (counts[i] >= 6) {
                        return i * 6;
                    }
                }
                for (int i = 1; i <= 6; i++) {
                    if (counts[i] >= 4) {
                        for (int j = 1; j <= 6; j++) {
                            if (j != i && counts[j] >= 2) {
                                return i * 4 + j * 2;
                            }
                        }
                    }
                }
                int pairs = 0;
                int score = 0;
                int[] counter = counts.clone();
                for (int i = 6; i >= 1; i--) {
                    if (counter[i] >= 2) {
                        pairs++;
                        score += i * 2;
                        counter[i] -= 2;
                        if (pairs == 3) {
                            return score;
                        }
                    }
                }
                return 0;
            }

            case FIVE_OF_A_KIND -> {
                for (int i = 1; i <= 6; i++) {
                    if (counts[i] >= 5)
                        return i * 5;
                }
                return 0;
            }

            case FULL_STRAIGHT -> {
                boolean allPresent = true;
                for (int i = 1; i <= 6; i++) {
                    if (counts[i] != 1) {
                        allPresent = false;
                        break;
                    }
                }
                return allPresent ? 21 : 0;
            }

            case TWO_TRIPS -> {
                for (int i = 1; i <= 6; i++) {
                    if (counts[i] >= 6) {
                        return i * 6;
                    }
                }
                int found = 0, score = 0;
                for (int i = 6; i >= 1; i--) {
                    if (counts[i] >= 3) {
                        found++;
                        score += i * 3;
                        counts[i] -= 3;
                        if (found == 2)
                            return score;
                    }
                }
                return 0;
            }

            case TOWER -> {
                for (int i = 1; i <= 6; i++) {
                    if (counts[i] >= 6) {
                        return i * 6;
                    }
                }
                boolean hasFour = false, hasTwo = false;
                int score = 0;
                for (int i = 6; i >= 1; i--) {
                    if (counts[i] >= 4 && !hasFour) {
                        hasFour = true;
                        score += i * 4;
                        counts[i] -= 4;
                    }
                }
                for (int i = 6; i >= 1; i--) {
                    if (counts[i] >= 2 && !hasTwo) {
                        hasTwo = true;
                        score += i * 2;
                    }
                }
                return (hasFour && hasTwo) ? score : 0;
            }
            case MAXI_YATZY -> {
                return findMaxiYatzy(counts);
            }

            default -> {
                try {
                    ScoreCategory mapped = ScoreCategory.valueOf(maxicategory.name());
                    ScoreCalculator standardCalculator = new ScoreCalculator();
                    return standardCalculator.calculateScore(dice, mapped);
                } catch (IllegalArgumentException e) {
                    return 0;
                }
            }
        }
    }

    private int findMaxiYatzy(int[] counts) {
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 6)
                return 100;
        }
        return 0;
    }

}
