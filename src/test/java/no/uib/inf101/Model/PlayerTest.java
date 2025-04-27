package no.uib.inf101.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.Model.player.Player;
import no.uib.inf101.Model.score.ScoreCategory;

public class PlayerTest {
    @Test
    public void testGetName() {
        Player p = new Player("Bernhard");
        assertEquals("Bernhard", p.getName());
    }

    @Test
    public void testScoreCardIntegration() {
        Player p = new Player("Tuva");
        p.getScoreCard().setScore(ScoreCategory.SIXES, 18);
        assertEquals(18, p.getScoreCard().getScore(ScoreCategory.SIXES));
    }

    @Test
    public void testToString() {
        Player p = new Player("Bernhard");
        assertEquals("Bernhard", p.toString());
    }
}
