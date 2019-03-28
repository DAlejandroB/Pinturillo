package com.edu.uptc.prg3.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static final int NUM_POINTS=5000;
	private Point[] coords;
	private int counter;
	private Color currentColor;
	
	public DrawPanel(boolean isDrawer) {
		super();
		this.coords = new Point[NUM_POINTS];
		counter=0;
		currentColor = Color.BLACK;
		if(isDrawer)
			this.addMouseMotionListener(this.generateMouseListener());
	}
	
	/**
	 * Overrides the paintComponent method, of the java components. Then, paint the canvas, with the 
	 * current coordinates
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(this.currentColor);
		for (int i = 0; i < counter; i++) {
			g.fillOval(coords[i].x, coords[i].y, 10, 10);		
		}
	}
	
	/**
	 * Disables the canvas draw area, if the player isn´t the drawer of the current round
	 * @param isDrawer boolean that indicates if the player is drawer or not
	 */
	public void flipDrawer(boolean isDrawer) {
		if(isDrawer) {
			this.addMouseMotionListener(this.generateMouseListener());
		}else {
			if(this.getMouseMotionListeners()!=null)
				this.removeMouseMotionListener(this.getMouseMotionListeners()[0]);
		}
			
	}
	
	/**
	 * Updates the canvas, with other points
	 * @param points the updated coordinates
	 */
	public void updateDrawPanel(Point[] points) {
		this.coords = points;
		this.repaint();
	}
	
	/**
	 * Changes the color of the draw
	 * @param newColor the new color to change
	 */
	public void changeColor(Color newColor) {
		this.currentColor = newColor;
	}
	
	/**
	 * Delete all the points drawed in the canvas. Later, the component is repainted
	 */
	public void cleanCanvas() {
		this.coords = new Point[NUM_POINTS];
		this.counter=0;
		this.repaint();
	}
	
	/**
	 * Generates a MouseMotionAdapter that will be used by the canvas
	 * @return a MouseMotionAdapter
	 */
	private MouseMotionAdapter generateMouseListener() {
		return new MouseMotionAdapter() {					
			@Override
			public void mouseDragged(MouseEvent e) {
				if(counter<coords.length) {
					coords[counter]= e.getPoint();
					counter++;
					repaint();
				}				
			}
		};
	}

	public Point[] getCoords() {
		return coords;
	}
	
	
}
