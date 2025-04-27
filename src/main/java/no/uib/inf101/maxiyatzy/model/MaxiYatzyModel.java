package no.uib.inf101.maxiyatzy.model;

import java.util.List;

import no.uib.inf101.Model.YatzyModel;
import no.uib.inf101.Model.dices.DiceSet;
import no.uib.inf101.Model.dices.RandomDieFactory;
import no.uib.inf101.Model.player.IPlayer;
import no.uib.inf101.maxiyatzy.model.player.MaxiPlayer;

/**
 * Extension of YatzyModel for MaxiYatzy.
 * Uses 6 dice and replaces players with MaxiPlayer instances.
 */
public class MaxiYatzyModel extends YatzyModel {

    /**
     * Constructs a new MaxiYatzyModel using MaxiPlayer and 6 dice.
     *
     * @param playerNames list of player names to include in the game.
     */
    public MaxiYatzyModel(List<String> playerNames) {
        super(playerNames);

        List<IPlayer> maxiPlayers = getPlayers();
        for (int i = 0; i < maxiPlayers.size(); i++) {
            String name = maxiPlayers.get(i).getName();
            maxiPlayers.set(i, new MaxiPlayer(name));
        }

        setDiceSet(new DiceSet(6, new RandomDieFactory()));
    }
}
