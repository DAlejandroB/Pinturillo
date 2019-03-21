package com.edu.uptc.prg3.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ProfileMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	private JMenu settingsMenu;
	private JMenuItem friendsItem, modInfoItem, deleteItem, logOutItem;
	
	public ProfileMenuBar(ActionListener actionListener) {
		friendsItem = new JMenuItem("Amigos");
		friendsItem.addActionListener(actionListener);
		friendsItem.setActionCommand("ver_amigos");
		friendsItem.setPreferredSize(new Dimension(50,10));
		this.add(friendsItem);
		
		settingsMenu = new JMenu("Opciones de Cuenta");
		modInfoItem = new JMenuItem("Modificar Informacion");
		modInfoItem.addActionListener(actionListener);
		modInfoItem.setActionCommand("modificar_info");
		settingsMenu.add(modInfoItem);
		
		deleteItem = new JMenuItem("Eliminar Cuenta");
		deleteItem.addActionListener(actionListener);
		deleteItem.setActionCommand("eliminar_cuenta");
		settingsMenu.add(deleteItem);
		
		logOutItem = new JMenuItem("Cerrar Sesion");
		logOutItem.addActionListener(actionListener);
		logOutItem.setActionCommand("cerrar_sesion");
		settingsMenu.add(logOutItem);
		
		this.add(settingsMenu);				
	}
}
