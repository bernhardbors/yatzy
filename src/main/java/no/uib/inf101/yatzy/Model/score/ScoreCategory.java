package no.uib.inf101.yatzy.Model.score;

/**
 * Enum representing the different score categories in Yatzy.
 * Each category corresponds to a specific scoring rule in the game.
 */
public enum ScoreCategory implements IScoreCategory {
    ONES, TWOS, THREES, FOURS, FIVES, SIXES,
    ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FOUR_OF_A_KIND,
    SMALL_STRAIGHT, LARGE_STRAIGHT, FULL_HOUSE, CHANCE, YATZY;

    @Override
    public String getDisplayName() {
        return switch (this) {
            case ONES -> "Enere";
            case TWOS -> "Toere";
            case THREES -> "Treere";
            case FOURS -> "Firere";
            case FIVES -> "Femmere";
            case SIXES -> "Seksere";
            case ONE_PAIR -> "Ett par";
            case TWO_PAIRS -> "To par";
            case THREE_OF_A_KIND -> "Tre like";
            case FOUR_OF_A_KIND -> "Fire like";
            case SMALL_STRAIGHT -> "Liten straight";
            case LARGE_STRAIGHT -> "Stor straight";
            case FULL_HOUSE -> "Hus (2+3)";
            case CHANCE -> "Sjanse";
            case YATZY -> "Yatzy";
        };
    }

    @Override
    public boolean isUpperCategory() {
        return switch (this) {
            case ONES, TWOS, THREES, FOURS, FIVES, SIXES -> true;
            default -> false;
        };
    }
}
