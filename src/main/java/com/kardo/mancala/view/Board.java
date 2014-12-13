package com.kardo.mancala.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Board extends JApplet {

	JPanel grava1;
	JPanel userBowls1;
	JPanel grava2;
	JPanel userBowls2;
	JPanel bowls;
	Container contentPane;
	final int width = 100;

	@Override
	public void init() {

		this.setSize(width*8, width*2);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		grava2 = new JPanel();
		grava2.setPreferredSize(new Dimension(width, width * 2));
		grava2.setBackground(Color.lightGray);
		userBowls1 = new JPanel(new GridLayout(1, 6));
		grava1 = new JPanel();
		grava1.setPreferredSize(new Dimension(width, width * 2));
		grava1.setBackground(Color.lightGray);
		userBowls2 = new JPanel(new GridLayout(1, 6));
		bowls = new JPanel();
		bowls.setLayout(new BoxLayout(bowls, BoxLayout.Y_AXIS));

		createBowls(userBowls1);
		createBowls(userBowls2);
		
		bowls.add(userBowls1);
		bowls.add(userBowls2);
		
		contentPane.add(grava2);
		contentPane.add(bowls);
		contentPane.add(grava1);
		

		this.setContentPane(contentPane);
		
	}

	public void createBowls(JPanel userBowls) {
		for(int i = 0; i < 6; i++) {
			JPanel bowl = new JPanel();
			bowl.setPreferredSize(new Dimension(width, width));
			bowl.setBackground(Color.yellow);
			bowl.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.GRAY));
			userBowls.add(bowl);
		}
	}
}
