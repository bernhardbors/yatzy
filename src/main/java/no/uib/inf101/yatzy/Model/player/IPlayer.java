package no.uib.inf101.yatzy.Model.player;

import no.uib.inf101.yatzy.Model.score.IScoreCard;

/**
 * Represents a player in a Yatzy game.
 * Each player has a name and a scorecard.
 */
public interface IPlayer {

    /**
     * Returns the name of the player.
     * 
     * @return the name of the player
     */
    String getName();

    /**
     * Returns the scorecard of the player.
     * 
     * @return the scorecard of the player
     */
    IScoreCard getScoreCard();
}
