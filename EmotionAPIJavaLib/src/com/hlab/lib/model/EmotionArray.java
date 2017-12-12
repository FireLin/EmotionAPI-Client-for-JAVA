package com.hlab.lib.model;

import java.util.ArrayList;

import com.hlab.lib.common.enums.Emotion;

public class EmotionArray {
	ArrayList<Double> emotions;
	int numberOfPeople;
	
	public EmotionArray(){
		numberOfPeople = 0;
		emotions = new ArrayList<Double>();
		for(int i=0; i<Emotion.values().length; i++){
			emotions.add(new Double(0));
		}
		System.out.println(emotions.size());
	}
	
	public double getEmotionValue(Emotion e){
		return this.emotions.get(e.index);
	}
	
	public void setEmotionValue(Emotion e, double value){
		this.emotions.set(e.index, value);
	}
	
	public double addToEmotionValue(Emotion e, double value){
		double finalvalue = this.emotions.get(e.index) + value;
		this.emotions.set(e.index, finalvalue);
		return this.emotions.get(e.index);
	}
	
	public void setNumberOfPeopleSeen(int number){
		this.numberOfPeople = number;
	}
	
	public int getNumberOfPeopleWithEmotions(){
		return this.numberOfPeople;
	}

	public Emotion getDominantEmotion(){
		int x = maxIndex(this.emotions);
		for(Emotion e: Emotion.values()){
			if(x == e.index){
				return e;
			}
		}
		return Emotion.Neutral;
	}
	
	private int maxIndex(ArrayList<Double> list) {
		  int i=0, maxIndex=-1;
		  Double max=null;
		  for (Double x : list) {
		    if ((x!=null) && ((max==null) || (x>max))) {
		      max = x;
		      maxIndex = i;
		    }
		    i++;
		  }
		  return maxIndex;
	}	
}
