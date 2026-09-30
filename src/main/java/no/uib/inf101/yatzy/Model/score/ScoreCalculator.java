package no.uib.inf101.yatzy.Model.score;

/**
 * Provides score calculation logic for each Yatzy category.
 */
public class ScoreCalculator implements IScoreCalculator {

    @Override
    public int calculateScore(int[] dice, IScoreCategory category) {
        if (!(category instanceof ScoreCategory sc)) {
            throw new IllegalArgumentException("Invalid category type for ScoreCalculator");
        }

        if (dice == null || dice.length == 0)
            throw new IllegalArgumentException("Dice array cannot be null or empty");

        int[] counts = countDice(dice);

        switch (sc) {
            case ONES, TWOS, THREES, FOURS, FIVES, SIXES -> {
                return counts[sc.ordinal() + 1] * (sc.ordinal() + 1);
            }
            case ONE_PAIR -> {
                return findOfAKind(counts, 2);
            }
            case TWO_PAIRS -> {
                return findTwoPairs(counts);
            }
            case THREE_OF_A_KIND -> {
                return findOfAKind(counts, 3);
            }
            case FOUR_OF_A_KIND -> {
                return findOfAKind(counts, 4);
            }
            case FULL_HOUSE -> {
                return findFullHouse(counts);
            }
            case SMALL_STRAIGHT -> {
                return isStraight(counts, 1, 5) ? 15 : 0;
            }
            case LARGE_STRAIGHT -> {
                return isStraight(counts, 2, 6) ? 20 : 0;
            }
            case CHANCE -> {
                return sum(dice);
            }
            case YATZY -> {
                return findYatzy(counts);
            }
            default -> {
                return 0;
            }
        }
    }

    private int[] countDice(int[] dice) {
        int[] counts = new int[7];
        for (int die : dice) {
            if (die < 1 || die > 6) {
                throw new IllegalArgumentException("Die value must be between 1 and 6");
            }
            counts[die]++;
        }
        return counts;
    }

    private int findOfAKind(int[] counts, int n) {
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= n)
                return i * n;
        }
        return 0;
    }

    private int findTwoPairs(int[] counts) {
        int pairs = 0;
        int score = 0;
        for (int i = 6; i >= 1; i--) {
            while (counts[i] >= 2 && pairs < 2) {
                pairs++;
                score += i * 2;
                counts[i] -= 2;
            }
            if (pairs == 2) {
                return score;
            }
        }
        return 0;
    }

    private int findFullHouse(int[] counts) {
        boolean hasThree = false, hasTwo = false;
        int threeVal = 0, twoVal = 0;

        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 3 && !hasThree) {
                hasThree = true;
                threeVal = i * 3;
                counts[i] -= 3;
            }
        }
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 2 && !hasTwo) {
                hasTwo = true;
                twoVal = i * 2;
            }
        }
        return (hasThree && hasTwo) ? (threeVal + twoVal) : 0;
    }

    private boolean isStraight(int[] counts, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (counts[i] == 0)
                return false;
        }
        return true;
    }

    private int findYatzy(int[] counts) {
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 5)
                return 100;
        }
        return 0;
    }

    private int sum(int[] dice) {
        int total = 0;
        for (int die : dice) {
            total += die;
        }
        return total;
    }

}