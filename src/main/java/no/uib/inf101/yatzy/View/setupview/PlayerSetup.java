package no.uib.inf101.yatzy.View.setupview;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides dialog-based player name setup for Yatzy and MaxiYatzy.
 */
public class PlayerSetup {

	/**
	 * Prompts the user for number of players and their names.
	 * If name is not provided, a default name is assigned.
	 *
	 * @return list of player names, or null if user cancels
	 */
	public static List<String> getPlayerNames() {
		List<String> playerNames = new ArrayList<>();

		Integer[] options = { 1, 2, 3, 4 };
		Integer count = (Integer) JOptionPane.showInputDialog(
				null,
				"Velg antall spillere",
				"Spilleroppsett",
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);

		if (count == null) {
			return null;
		}

		for (int i = 1; i <= count; i++) {
			String name = JOptionPane.showInputDialog(
					null,
					"Navn på spiller " + i + ":",
					"Spillernavn",
					JOptionPane.PLAIN_MESSAGE);
			if (name == null || name.isBlank()) {
				name = "Spiller " + i;
			}
			playerNames.add(name.trim());
		}

		return playerNames;
	}

}
