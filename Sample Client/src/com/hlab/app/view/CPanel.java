package com.hlab.app.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.hlab.app.viewcontroller.CPanelController;

/**
 * @author homran
 *
 */
public class CPanel extends JPanel implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton emotionRecoBtn;
	JLabel numOfPeopleSeenLbl;
	ChartPanel chartpanel;
	CPanelController viewController;
	
	public CPanel(){
		super();
		viewController = new CPanelController();
		initComponents();
	}
	
	public void initComponents(){
		emotionRecoBtn = new JButton();
		numOfPeopleSeenLbl = new JLabel();
		chartpanel = new ChartPanel(null);
		
		this.setBackground(Color.GRAY);
		
		emotionRecoBtn.setBounds(100, 100, 150, 150);
		emotionRecoBtn.setText("Emotion Recognition ON");
		this.add(emotionRecoBtn);
		emotionRecoBtn.addActionListener(this);
		
		numOfPeopleSeenLbl.setBounds(100,200,150,150);
		this.add(numOfPeopleSeenLbl);
		viewController.bindNumOfPeopleSeenToGUI(numOfPeopleSeenLbl);
		
		chartpanel.setBounds(100,300,200,200);
		this.add(chartpanel);
		viewController.bindPieChartToGUI(chartpanel);
		chartpanel.setVisible(true);
		
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
