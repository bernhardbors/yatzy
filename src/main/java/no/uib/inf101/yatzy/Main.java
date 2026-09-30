package no.uib.inf101.yatzy;

import javax.swing.*;

import no.uib.inf101.yatzy.View.setupview.StartScreenPanel;

/**
 * Entry point for the Yatzy application.
 * Displays the start screen for selecting game type.
 */
public class Main {
	public static final String WINDOW_TITLE = "Yatzy!";

	/**
	 * Launches the game with a start menu for Yatzy and MaxiYatzy.
	 *
	 * @param args Command line arguments (unused)
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame(WINDOW_TITLE);

		StartScreenPanel startPanel = new StartScreenPanel(isMaxiYatzy -> {
			frame.dispose();
			GameLauncher.startGame(isMaxiYatzy);
		});

		frame.setContentPane(startPanel);
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}