package no.uib.inf101.Model;

import java.util.List;

import no.uib.inf101.Model.dices.DiceSet;
import no.uib.inf101.Model.dices.IDiceSet;
import no.uib.inf101.Model.dices.IDie;
import no.uib.inf101.Model.dices.RandomDieFactory;
import no.uib.inf101.Model.player.IPlayer;
import no.uib.inf101.Model.player.Player;

import java.util.ArrayList;

/**
 * Represents the model of a Yatzy game.
 * Handles player management, dice rolling, turn logic, and score tracking.
 */
public class YatzyModel {
    private final List<IPlayer> players;
    private int currentPlayerIndex = 0;

    private IDiceSet diceSet;
    private int rollsThisTurn;

    /**
     * Constructs a new YatzyModel with the given player names.
     * 
     * @param playerNames a list of player names
     * @throws NullPointerException if playerNames is null
     */
    public YatzyModel(List<String> playerNames) {
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        this.diceSet = new DiceSet(5, new RandomDieFactory());
        this.rollsThisTurn = 0;
    }

    /**
     * Rolls all unheld dice if fewer than 3 rolls have been made.
     */
    public void rollDice() {
        if (rollsThisTurn < 3) {
            diceSet.rollAll();
            rollsThisTurn++;
        }
    }

    /**
     * Starts a new turn by releasing dice and moving to next player.
     */
    public void newTurn() {
        rollsThisTurn = 0;
        diceSet.releaseAll();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    /**
     * Returns the current set of dice.
     * 
     * @return the current set of dice
     */
    public IDie[] getDice() {
        return diceSet.getAllDice();
    }

    /**
     * Creates a new set of dice.
     */
    protected void setDiceSet(IDiceSet newDiceSet) {
        this.diceSet = newDiceSet;
    }

    /**
     * Returns the number of rolls made in the current turn.
     * 
     * @return the number of rolls made in the current turn
     */
    public int getRollsThisTurn() {
        return rollsThisTurn;
    }

    /**
     * Simulates a roll without incrementing the roll counter.
     * Used for animations.
     */
    public void simulateRoll() {
        diceSet.rollAll();
    }

    /**
     * Returns the current player.
     * 
     * @return the current player
     */
    public IPlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Returns the list of players in the game.
     * 
     * @return the list of players
     */
    public List<IPlayer> getPlayers() {
        return players;
    }

    /**
     * Determines the winner of the game based on the highest score.
     * 
     * @return the player with the highest score
     */
    public IPlayer getWinner() {
        IPlayer winner = null;
        int highestScore = -1;

        for (IPlayer player : players) {
            int score = player.getScoreCard().getTotalScore();
            if (score > highestScore) {
                highestScore = score;
                winner = player;
            }
        }

        return winner;
    }

    /**
     * Checks if all players have filled their scorecards.
     * 
     * @return true if the game is finished, false otherwise
     */
    public boolean isGameFinished() {
        for (IPlayer p : players) {
            if (!p.getScoreCard().isFinished()) {
                return false;
            }
        }
        return true;
    }

}
