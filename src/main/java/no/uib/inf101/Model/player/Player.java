package no.uib.inf101.Model.player;

import no.uib.inf101.Model.score.IScoreCard;
import no.uib.inf101.Model.score.ScoreCard;
import no.uib.inf101.Model.score.ScoreCategory;

/**
 * Default implementation of a player in the Yatzy game.
 * Stores the player's name and their scorecard.
 */
public class Player implements IPlayer {
    private final String name;
    private final IScoreCard scoreCard = new ScoreCard<>(ScoreCategory.values());

    /**
     * Constructs a player with the given name.
     * 
     * @param name the name of the player
     * @throws NullPointerException if name is null
     */
    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IScoreCard getScoreCard() {
        return scoreCard;
    }

    @Override
    public String toString() {
        return name;
    }
}
