package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawAreaPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private DrawPanel drawPanel;
	private JPanel colorsPanel;
	private JButton btnRed, btnPurple, btnBlue, btnGreen, btnYellow, 
				btnOrange, btnWhite, btnGray, btnBlack, btnCleanAll;
	
	public DrawAreaPanel(boolean isDrawer) {
		this.setLayout(new BorderLayout());	
		this.setBackground(new Color(137, 238, 157));
		drawPanel = new DrawPanel(isDrawer);
		drawPanel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/2, 
									Toolkit.getDefaultToolkit().getScreenSize().height-150));
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(drawPanel, BorderLayout.NORTH);
		colorsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipady = 10;
		btnRed = new JButton("");
		btnRed.addActionListener(this);
		btnRed.setActionCommand("red");
		btnRed.setBackground(Color.RED);
		btnRed.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=0;
		gbc.gridy=0;
		colorsPanel.add(btnRed, gbc);
		
		btnPurple = new JButton("");
		btnPurple.addActionListener(this);
		btnPurple.setActionCommand("purple");
		btnPurple.setBackground(Color.MAGENTA);
		btnPurple.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=1;
		colorsPanel.add(btnPurple, gbc);
		
		btnBlue = new JButton("");
		btnBlue.addActionListener(this);
		btnBlue.setActionCommand("blue");
		btnBlue.setBackground(Color.BLUE);
		btnBlue.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=2;
		colorsPanel.add(btnBlue, gbc);
		
		btnGreen = new JButton("");
		btnGreen.addActionListener(this);
		btnGreen.setActionCommand("green");
		btnGreen.setBackground(Color.GREEN);
		btnGreen.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=3;
		colorsPanel.add(btnGreen, gbc);
		
		btnYellow = new JButton("");
		btnYellow.addActionListener(this);
		btnYellow.setActionCommand("yellow");
		btnYellow.setBackground(Color.YELLOW);
		btnYellow.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=4;
		colorsPanel.add(btnYellow, gbc);
		
		btnOrange = new JButton("");
		btnOrange.addActionListener(this);
		btnOrange.setActionCommand("orange");
		btnOrange.setBackground(Color.ORANGE);
		btnOrange.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=0;
		gbc.gridy=1;
		colorsPanel.add(btnOrange, gbc);
		
		btnWhite = new JButton("");
		btnWhite.addActionListener(this);
		btnWhite.setActionCommand("white");
		btnWhite.setBackground(Color.WHITE);
		btnWhite.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=1;
		colorsPanel.add(btnWhite, gbc);
		
		btnGray = new JButton("");
		btnGray.addActionListener(this);
		btnGray.setActionCommand("gray");
		btnGray.setBackground(Color.GRAY);
		btnGray.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=2;
		colorsPanel.add(btnGray, gbc);
		
		btnBlack = new JButton("");
		btnBlack.addActionListener(this);
		btnBlack.setActionCommand("black");
		btnBlack.setBackground(Color.BLACK);
		btnBlack.setPreferredSize(new Dimension(60, 30));
		gbc.gridx=3;
		colorsPanel.add(btnBlack, gbc);
		
		btnCleanAll = new JButton("Limpiar");
		btnCleanAll.addActionListener(this);
		btnCleanAll.setActionCommand("clean");
		gbc.gridx=4;
		colorsPanel.add(btnCleanAll, gbc);		
		colorsPanel.setBackground(new Color(137, 238, 157));
		this.add(colorsPanel, BorderLayout.SOUTH);
	}

	/**
	 * Calls the flipDrawer method of the drawPanel, and flips the state of the player in the game
	 * @param isDrawer boolean that indicates if the player is or isn´t the current drawer
	 */
	public void flipDrawer(boolean isDrawer) {
		this.drawPanel.flipDrawer(isDrawer);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "red":
				this.drawPanel.changeColor(Color.RED);
				break;
			case "purple":
				this.drawPanel.changeColor(Color.MAGENTA);
				break;
			case "blue":
				this.drawPanel.changeColor(Color.BLUE);
				break;
			case "green":
				this.drawPanel.changeColor(Color.GREEN);
				break;
			case "yellow":
				this.drawPanel.changeColor(Color.YELLOW);
				break;
			case "orange":
				this.drawPanel.changeColor(Color.ORANGE);
				break;
			case "white":
				this.drawPanel.changeColor(Color.WHITE);
				break;
			case "gray":
				this.drawPanel.changeColor(Color.GRAY);
				break;	
			case "black":
				this.drawPanel.changeColor(Color.BLACK);
				break;
			case "clean":
				this.drawPanel.cleanCanvas();
				break;	
		}
	}
}
