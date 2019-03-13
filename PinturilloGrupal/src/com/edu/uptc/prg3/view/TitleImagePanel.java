package com.edu.uptc.prg3.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TitleImagePanel  extends JPanel{
    public TitleImagePanel() {
        FlowLayout layout = new FlowLayout();
        layout.setHgap(0);
        layout.setVgap(0);
        setLayout(layout);
        ImageIcon iconImg = new ImageIcon("data/Login_Title.png");
        JLabel lblImage = new JLabel("");
        lblImage.setIcon(iconImg);
        add(lblImage);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.BLACK));
    }	
}
