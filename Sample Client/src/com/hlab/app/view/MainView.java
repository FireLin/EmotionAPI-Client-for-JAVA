package com.hlab.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

/**
 * @author homran
 *
 */
public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CPanel thePanel;
	
	public MainView(){
		super();
		this.setBackground(Color.white);
		this.setSize(new Dimension(800, 200));
		this.setLocation(new Point(10, 10));
		thePanel = new CPanel();
		this.add(thePanel);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		MainView frame = new MainView();
		frame.setVisible(true);
		//frame.setResizable(false);
	}
	
	
}
