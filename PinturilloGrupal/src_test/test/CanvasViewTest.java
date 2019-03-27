package test;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.edu.uptc.prg3.view.DrawAreaPanel;
import com.edu.uptc.prg3.view.DrawPanel;

public class CanvasViewTest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Prueba de area de dibujo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height));
		frame.setResizable(false);
		frame.add(new DrawAreaPanel(true));			
		frame.setVisible(true);
		
	}
}
