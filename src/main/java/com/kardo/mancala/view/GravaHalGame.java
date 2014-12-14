package com.kardo.mancala.view;

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
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.kardo.mancala.controller.Game;
import com.kardo.mancala.model.AbstractBowl;

public class GravaHalGame extends JApplet {

	JPanel grava1;
	JPanel userBowls1;
	JPanel grava2;
	JPanel userBowls2;
	JPanel bowls;
	Container contentPane;
	final int width = 100;
	Game game;
	ArrayList<JPanel> boardGUI;

	@Override
	public void init() {

		this.setSize(width*8, width*2);
		
		boardGUI = new ArrayList<>();
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		grava2 = new JPanel();
		grava2.setPreferredSize(new Dimension(width, width * 2));
		grava2.setBackground(Color.lightGray);
		userBowls1 = new JPanel(new GridLayout(1, 6));
		userBowls1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		grava1 = new JPanel();
		grava1.setPreferredSize(new Dimension(width, width * 2));
		grava1.setBackground(Color.lightGray);
		userBowls2 = new JPanel(new GridLayout(1, 6));
		userBowls2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		bowls = new JPanel();
		bowls.setLayout(new BoxLayout(bowls, BoxLayout.Y_AXIS));

		game = new Game();
		
		createBowls(userBowls1);
		boardGUI.add(grava1);
		createBowls(userBowls2);
		boardGUI.add(grava2);
		refreshSeeds();

		bowls.add(userBowls2);
		bowls.add(userBowls1);
		
		contentPane.add(grava2);
		contentPane.add(bowls);
		contentPane.add(grava1);
		

		this.setContentPane(contentPane);
		
	}

	public void createBowls(JPanel userBowls) {
		for(int i = 0; i < 6; i++) {
			final JPanel bowl = new JPanel();
			bowl.setPreferredSize(new Dimension(width, width));
			bowl.setBackground(Color.yellow);
			bowl.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.GRAY));
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
					game.distributeSeeds(boardGUI.indexOf(e.getComponent()));	
					refreshSeeds();
				}
			});
		}
	}
	
	public void refreshSeeds() {
		ArrayList<AbstractBowl> board = game.getBoard();
		for(int i = 0; i < board.size(); i++) {
			int seeds = board.get(i).getSeeds();
			createSeeds(boardGUI.get(i), seeds);		
		}
		
	}

	private void createSeeds(JPanel bowl, int seeds) {
		bowl.removeAll();
		bowl.setLayout(new FlowLayout(FlowLayout.CENTER));
		for(int i = 0; i < seeds; i++){
			bowl.add(new Seed());
		}
		bowl.updateUI();
	}
	
	
	
	class Seed extends JPanel {
	    @Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(Color.RED);
	        g2d.fillOval(0, 0, 20, 20);
	    }
	    
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(20, 20);
        }
        
        @Override
        public Color getBackground() {
        	return Color.yellow;
        }

	}
	
}
