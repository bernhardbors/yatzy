package no.uib.inf101.yatzy.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.yatzy.Model.dices.IDie;
import no.uib.inf101.yatzy.Model.player.IPlayer;
import no.uib.inf101.yatzy.Model.score.ScoreCategory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class YatzyModelTest {

    private YatzyModel model;

    @BeforeEach
    public void setup() {
        model = new YatzyModel(List.of("Bernhard", "Tuva"));
    }

    @Test
    public void testInitialPlayerIsCorrect() {
        assertEquals("Bernhard", model.getCurrentPlayer().getName());
    }

    @Test
    public void testInitialRollsIsZero() {
        assertEquals(0, model.getRollsThisTurn());
    }

    @Test
    public void testRollDiceIncrementsRolls() {
        model.rollDice();
        assertEquals(1, model.getRollsThisTurn());
        model.rollDice();
        model.rollDice();
        model.rollDice();
        assertEquals(3, model.getRollsThisTurn());
    }

    @Test
    public void testRollDiceActuallyRolls() {
        int[] beforeRoll = getDiceValues(model.getDice());
        model.rollDice();
        int[] afterRoll = getDiceValues(model.getDice());
        assertFalse(allDiceSame(beforeRoll, afterRoll));
    }

    @Test
    public void testSimulateRollChangesDice() {
        int[] beforeSim = getDiceValues(model.getDice());
        model.simulateRoll();
        int[] afterSim = getDiceValues(model.getDice());
        assertFalse(allDiceSame(beforeSim, afterSim));
    }

    @Test
    public void testNewTurnAndResetsRolls() {
        IPlayer first = model.getCurrentPlayer();
        model.rollDice();
        model.newTurn();
        IPlayer second = model.getCurrentPlayer();
        assertNotEquals(first, second);
        assertEquals(0, model.getRollsThisTurn());
    }

    @Test
    public void testNewTurnReleasesAllDice() {
        model.rollDice();
        for (var die : model.getDice()) {
            die.toggleHeld();
        }
        model.newTurn();
        for (var die : model.getDice()) {
            assertFalse(die.isHeld());
        }
    }

    @Test
    public void testGetDiceReturnsFiveDice() {
        assertEquals(5, model.getDice().length);
    }

    @Test
    public void testGameIsNotFinishedInitially() {
        assertFalse(model.isGameFinished());
    }

    @Test
    public void testGameIsFinishedWhenAllPlayersFinished() {
        for (IPlayer player : model.getPlayers()) {
            for (ScoreCategory cat : ScoreCategory.values()) {
                player.getScoreCard().setScore(cat, 5);
            }
        }
        assertTrue(model.isGameFinished());
    }

    @Test
    public void testGetWinner() {
        IPlayer bernhard = model.getCurrentPlayer();
        for (ScoreCategory cat : ScoreCategory.values()) {
            bernhard.getScoreCard().setScore(cat, 5);
        }

        model.newTurn();
        IPlayer tuva = model.getCurrentPlayer();
        for (ScoreCategory cat : ScoreCategory.values()) {
            tuva.getScoreCard().setScore(cat, 3);
        }

        assertEquals(bernhard, model.getWinner());
    }

    @Test
    public void testGetWinnerWithSameScore() {
        IPlayer first = model.getPlayers().get(0);
        IPlayer second = model.getPlayers().get(1);

        for (ScoreCategory cat : ScoreCategory.values()) {
            first.getScoreCard().setScore(cat, 1);
            second.getScoreCard().setScore(cat, 1);
        }

        assertEquals(first, model.getWinner());
    }

    // Helper methods

    private int[] getDiceValues(IDie[] dice) {
        int[] values = new int[dice.length];
        for (int i = 0; i < dice.length; i++) {
            values[i] = dice[i].getValue();
        }
        return values;
    }

    private boolean allDiceSame(int[] before, int[] after) {
        if (before.length != after.length)
            return false;
        for (int i = 0; i < before.length; i++) {
            if (before[i] != after[i])
                return false;
        }
        return true;
    }
}
