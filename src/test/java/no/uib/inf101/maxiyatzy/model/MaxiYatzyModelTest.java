package no.uib.inf101.maxiyatzy.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.Model.dices.IDie;
import no.uib.inf101.Model.player.IPlayer;

public class MaxiYatzyModelTest {

    private MaxiYatzyModel model;

    @BeforeEach
    public void setup() {
        model = new MaxiYatzyModel(List.of("Bernhard", "Tuva"));
    }

    @Test
    public void testDiceCountIsSix() {
        IDie[] dice = model.getDice();
        assertEquals(6, dice.length);
    }

    @Test
    public void testTurnAdvancesToNextPlayer() {
        IPlayer first = model.getCurrentPlayer();
        model.newTurn();
        IPlayer second = model.getCurrentPlayer();
        assertNotEquals(first, second);
    }

    @Test
    public void testGameNotFinishedInitially() {
        assertFalse(model.isGameFinished());
    }
}
