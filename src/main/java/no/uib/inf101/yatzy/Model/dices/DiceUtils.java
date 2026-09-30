package no.uib.inf101.yatzy.Model.dices;

/**
 * Utility methods for working with dice in the Yatzy game.
 */
public class DiceUtils {

    /**
     * Extracts the face values from an array of dice.
     * 
     * @param dice an array of IDie
     * @return an array of integers representing the face values
     */
    public static int[] getValues(IDie[] dice) {
        int[] values = new int[dice.length];
        for (int i = 0; i < dice.length; i++) {
            values[i] = dice[i].getValue();
        }
        return values;
    }

    /**
     * Counts the occurrences of each die value.
     * 
     * @param dice an array of dice values (1–6)
     * @return an array where index represents the die value and value represents
     *         the count
     * @throws IllegalArgumentException if any die value is not between 1 and 6
     */
    public static int[] countDice(int[] dice) {
        int[] counts = new int[7]; // Index 0 is unused
        for (int die : dice) {
            if (die < 1 || die > 6) {
                throw new IllegalArgumentException("Die value must be between 1 and 6");
            }
            counts[die]++;
        }
        return counts;
    }

}
