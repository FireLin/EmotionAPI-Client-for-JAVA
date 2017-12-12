package com.hlab.app.viewcontroller;

import java.awt.Color;
import java.util.Random;

//import java.io.File;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.hlab.lib.model.EmotionArray;
import com.hlab.lib.common.enums.Emotion;
import com.hlab.lib.common.controller.EmotionAPIController;
import com.hlab.lib.common.controller.WebCamController;

/**
 * @author homran
 *
 */
public class CPanelController {
	
	EmotionArray emotionalArray;
	boolean isEmotionOn = false;
	JLabel numOfPeopleSeenLabel;
	int numOfPeopleSeen;
	ChartPanel chartPanel;
	DefaultPieDataset dataset;
	
	public CPanelController(){
		dataset = new DefaultPieDataset( );
		dataset.setValue( Emotion.Positive , new Double( 10 ) );  
	    dataset.setValue( Emotion.Negative , new Double( 10 ) );   
	    dataset.setValue( Emotion.Angry , new Double( 10 ) );    
	    dataset.setValue( Emotion.Surprised , new Double( 10 ) );
	    dataset.setValue( Emotion.Neutral , new Double( 10 ) );
		numOfPeopleSeen = 0;
		
		RunLoop runLoop = new RunLoop();
		Thread threadLoop = new Thread(runLoop);
		threadLoop.start();
	}
	
	
	public void bindNumOfPeopleSeenToGUI(JLabel label){
		numOfPeopleSeenLabel = label;
	}
	
	public void bindPieChartToGUI(ChartPanel panel){
	   chartPanel = panel;
	}
	
	protected void updateDataForUI(){
		numOfPeopleSeenLabel.setText(
				String.format("Number of People: %d", numOfPeopleSeen));
		
		if(emotionalArray != null){
			dataset = new DefaultPieDataset( );
			dataset.setValue( Emotion.Positive , emotionalArray.getEmotionValue(Emotion.Positive) );  
			dataset.setValue( Emotion.Negative , emotionalArray.getEmotionValue(Emotion.Negative) );   
			dataset.setValue( Emotion.Angry , emotionalArray.getEmotionValue(Emotion.Angry) );    
			dataset.setValue( Emotion.Surprised , emotionalArray.getEmotionValue(Emotion.Surprised));
			dataset.setValue( Emotion.Neutral , emotionalArray.getEmotionValue(Emotion.Neutral));
		}
	    
	    chartPanel.removeAll();
		JFreeChart chart = createChart(dataset);
		chartPanel.setChart(chart);
		chartPanel.setBackground(Color.black);
		chartPanel.revalidate();
    }
	
	public JFreeChart createChart(DefaultPieDataset Dateset){
		JFreeChart chart = ChartFactory.createPieChart(      
		        "Mobile Sales",   // chart title 
		         dataset,          // data    
		         true,             // include legend   
		         true, 
		         false);

		      return chart;
	}
	
class RunLoop implements Runnable{

	@Override
	public void run() {
		/**/
		System.out.println("Starting...");
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			emotionalArray = EmotionAPIController.getInstance()
					.getEmotionsFromImage(WebCamController.getInstance()
							.getImageSeenByCamera());
			if(emotionalArray != null){	
				numOfPeopleSeen = emotionalArray.getNumberOfPeopleWithEmotions();
				updateDataForUI();
			}
		}
	}
	
}
	
	
	
}
