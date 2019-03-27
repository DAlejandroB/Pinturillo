package com.edu.uptc.prg3.view;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ProfileMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	private JMenu settingsMenu, friendsMenu;
	private JMenuItem friendsItem, modInfoItem, deleteItem, logOutItem;
	
	public ProfileMenuBar(ActionListener actionListener) {
		
		friendsMenu = new JMenu("Amigos");
		friendsItem = new JMenuItem("Ver Amigos");
		friendsItem.addActionListener(actionListener);
		friendsItem.setActionCommand("ver_amigos");
		friendsMenu.add(friendsItem);
		this.add(friendsMenu);
		
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
