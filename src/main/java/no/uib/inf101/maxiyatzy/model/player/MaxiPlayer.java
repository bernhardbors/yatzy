package no.uib.inf101.maxiyatzy.model.player;

import no.uib.inf101.Model.player.IPlayer;
import no.uib.inf101.maxiyatzy.model.score.MaxiScoreCard;
import no.uib.inf101.Model.score.IScoreCard;

/**
 * Represents a player in the MaxiYatzy variant with a MaxiScoreCard.
 */
public class MaxiPlayer implements IPlayer {
    private final String name;
    private final MaxiScoreCard scoreCard = new MaxiScoreCard();

    /**
     * Constructs a MaxiPlayer with the given name.
     *
     * @param name the player's name
     */
    public MaxiPlayer(String name) {
        this.name = name;
    }

    @Override
    public IScoreCard getScoreCard() {
        return scoreCard;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
