package no.uib.inf101.maxiyatzy.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.Model.score.IScoreCard;
import no.uib.inf101.maxiyatzy.model.score.MaxiScoreCard;

public class MaxiPlayerTest {

    @Test
    public void testNameIsSetCorrectly() {
        MaxiPlayer player = new MaxiPlayer("Bernhard");
        assertEquals("Bernhard", player.getName());
        assertEquals("Bernhard", player.toString());
    }

    @Test
    public void testScoreCardIsMaxiScoreCard() {
        MaxiPlayer player = new MaxiPlayer("Tuva");
        IScoreCard card = player.getScoreCard();
        assertTrue(card instanceof MaxiScoreCard);
    }
}
