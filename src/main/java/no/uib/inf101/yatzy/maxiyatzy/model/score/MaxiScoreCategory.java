package no.uib.inf101.yatzy.maxiyatzy.model.score;

import no.uib.inf101.yatzy.Model.score.IScoreCategory;

/**
 * Extra categories for MaxiYatzy variant. Implements IScoreCategory.
 */
public enum MaxiScoreCategory implements IScoreCategory {
    ONES, TWOS, THREES, FOURS, FIVES, SIXES,
    ONE_PAIR, TWO_PAIRS, THREE_PAIRS, THREE_OF_A_KIND, FOUR_OF_A_KIND, FIVE_OF_A_KIND,
    SMALL_STRAIGHT, LARGE_STRAIGHT, FULL_STRAIGHT, FULL_HOUSE, TWO_TRIPS, TOWER, CHANCE, MAXI_YATZY;

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
            case THREE_PAIRS -> "Tre par";
            case THREE_OF_A_KIND -> "Tre like";
            case FOUR_OF_A_KIND -> "Fire like";
            case FIVE_OF_A_KIND -> "Fem like";
            case SMALL_STRAIGHT -> "Liten straight";
            case LARGE_STRAIGHT -> "Stor straight";
            case FULL_STRAIGHT -> "Full straight";
            case FULL_HOUSE -> "Hytte (2+3)";
            case TWO_TRIPS -> "Hus (3+3)";
            case TOWER -> "Tårn (4+2)";
            case CHANCE -> "Sjanse";
            case MAXI_YATZY -> "Maxi Yatzy";
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
