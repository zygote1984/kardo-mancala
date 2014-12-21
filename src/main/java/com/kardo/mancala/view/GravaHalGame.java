package com.kardo.mancala.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.kardo.mancala.controller.Game;
import com.kardo.mancala.controller.GameConstants;
import com.kardo.mancala.model.AbstractBowl;

/**
 * Implements the UI of the game.
 * @author kardo
 *
 */
public class GravaHalGame extends JApplet {

	private static final Color BG = Color.decode("#FFDBDB");
	private static final String BOWL_BG = "#FFEDA9";
	private static final String TURN_COLOR = "#FF9900";
	JPanel grava1;
	JPanel userBowls1;
	JPanel grava2;
	JPanel userBowls2;
	JPanel bowls;
	Container contentPane;
	final int width = 150;
	Game game;
	ArrayList<JPanel> boardGUI;
	JLabel user1Label;
	JLabel user2Label;
	private JPanel gamePanel;
	private JPanel upperPanel;
	private JPanel lowerPanel;

	@Override
	public void init() {

		this.setSize(width * 8, width * 2 + 50);

		game = new Game();
		boardGUI = new ArrayList<>();
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		gamePanel = new JPanel();
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));
		upperPanel = new JPanel(new GridLayout(1, 8));
		upperPanel.setPreferredSize(new Dimension(width * 8, 25));
		lowerPanel = new JPanel(new GridLayout(1, 8));
		lowerPanel.setPreferredSize(new Dimension(width * 8, 25));
		user1Label = new JLabel("Player 1", SwingConstants.CENTER);
		user1Label.setOpaque(true);
		user2Label = new JLabel("Player 2", SwingConstants.CENTER);
		user2Label.setOpaque(true);

		grava2 = new JPanel();
		grava2.setPreferredSize(new Dimension(width, width * 2));
		grava2.setBackground(BG);
		grava2.setLayout(new FlowLayout());

		userBowls1 = new JPanel(new GridLayout(1, 6));
		userBowls1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		grava1 = new JPanel();
		grava1.setLayout(new FlowLayout());
		grava1.setPreferredSize(new Dimension(width, width * 2));
		grava1.setBackground(BG);

		userBowls2 = new JPanel(new GridLayout(1, 6));
		userBowls2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		bowls = new JPanel();
		bowls.setLayout(new BoxLayout(bowls, BoxLayout.Y_AXIS));

		createBowls(userBowls1);
		boardGUI.add(grava1);
		createBowls(userBowls2);
		boardGUI.add(grava2);
		refreshSeeds(0);

		refreshInfoPanels();

		bowls.add(userBowls2);
		bowls.add(userBowls1);

		gamePanel.add(grava2);
		gamePanel.add(bowls);
		gamePanel.add(grava1);

		contentPane.add(upperPanel, BorderLayout.NORTH);
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(lowerPanel, BorderLayout.SOUTH);

		this.setContentPane(contentPane);

	}

	private void refreshInfoPanels() {
		upperPanel.removeAll();
		lowerPanel.removeAll();
		

		upperPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		upperPanel.add(user1Label);
		for (int i = 7; i < 13; i++) {
			upperPanel.add(new JLabel(String.valueOf(boardGUI.get(i)
					.getComponentCount()), SwingConstants.CENTER));
		}
		upperPanel.add(user2Label);
		
		lowerPanel.add(new JLabel(String.valueOf(boardGUI.get(13)
				.getComponentCount()),SwingConstants.CENTER));
		for (int i = 0; i < 7; i++) {
			lowerPanel.add(new JLabel(String.valueOf(boardGUI.get(i)
					.getComponentCount()),SwingConstants.CENTER));
		}
		
		setTurnColor();

	}

	private void setTurnColor() {
		if (game.getTurn() == GameConstants.PLAYER_1) {
			user1Label.setBackground(Color.decode(TURN_COLOR));
			user2Label.setBackground(BG);
		} else if (game.getTurn() == GameConstants.PLAYER_2) {
			user2Label.setBackground(Color.decode(TURN_COLOR));
			user1Label.setBackground(BG);
		}
		user1Label.repaint();
		user2Label.repaint();
		grava1.validate();
		grava2.validate();
	}

	public void createBowls(JPanel userBowls) {
		for (int i = 0; i < 6; i++) {
			final JPanel bowl = new JPanel();
			bowl.setPreferredSize(new Dimension(width, width));
			bowl.setBackground(Color.decode(BOWL_BG));
			bowl.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,
					Color.lightGray, Color.GRAY));
			userBowls.add(bowl);
			boardGUI.add(bowl);
			bowl.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (game.distributeSeeds(boardGUI.indexOf(e.getComponent()))) {
						refreshSeeds(boardGUI.indexOf(e.getComponent()));
						refreshInfoPanels();
					}
					
				}
			});
		}
	}

	public void refreshSeeds(int index) {
		ArrayList<AbstractBowl> board = game.getBoard();
		for (int i = 0; i < board.size(); i++) {
			int seeds = board.get(i).getSeeds();
			createSeeds(boardGUI.get(i), seeds);
		}
	}

	private void createSeeds(JPanel bowl, int seeds) {
		bowl.removeAll();
		bowl.setLayout(new FlowLayout(FlowLayout.CENTER));
		for (int i = 0; i < seeds; i++) {
			bowl.add(new Seed());
		}
		bowl.updateUI();
	}

	class Seed extends JPanel {
		private static final String SEED_COLOR = "#5A0000";

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.decode(SEED_COLOR));
			g2d.fillOval(0, 0, 20, 20);
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(20, 20);
		}

		@Override
		public boolean isOpaque() {
			return false;
		}

	}

}
