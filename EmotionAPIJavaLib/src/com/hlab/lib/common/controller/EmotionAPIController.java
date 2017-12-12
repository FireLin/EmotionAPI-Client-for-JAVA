package com.hlab.lib.common.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.hlab.lib.model.ConfigFile;
import com.hlab.lib.model.EmotionArray;
import com.hlab.lib.common.enums.Emotion;
import com.hlab.lib.emotionanalysisapi.EmotionRestClient;
import com.hlab.lib.emotionanalysisapi.models.FaceAnalysis;
import retrofit2.Response;

public class EmotionAPIController {
	
	private static EmotionAPIController instance = null;
	
	protected EmotionAPIController(){
		ConfigFile properties = new ConfigFile();
		EmotionRestClient.init(properties.getEmotionAPIKey());
	}
	
	public static EmotionAPIController getInstance(){
		if(instance == null){
			instance = new EmotionAPIController();
		}
		return instance;
	}
	
	private FaceAnalysis[] getFacesData(File x){
		Response<FaceAnalysis[]> res = null;
		try {
			res = EmotionRestClient.getInstance()
					.detect("file://"+x.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res != null){
	    	FaceAnalysis[] faces = res.body();
	    	String status = res.message().toString();
	    	System.out.println(status);
	    	return faces;
		}
		return null;
	}
	
	public EmotionArray getEmotionsFromImage(File x){
		FaceAnalysis[] faces = getFacesData(x);
		EmotionArray myEmotions = new EmotionArray();
		int scale = 100;
		if(faces != null){
			myEmotions.setNumberOfPeopleSeen(faces.length);
			for(int i=0; i<faces.length; i++){
				double positive = faces[i].getScores().getHappiness();

				double negative = faces[i].getScores().getSadness() 
						+ faces[i].getScores().getDisgust() 
						+ faces[i].getScores().getContempt() 
						+ faces[i].getScores().getFear();

				double angry = faces[i].getScores().getAnger();
				double neutral = faces[i].getScores().getNeutral();
				double suprise = faces[i].getScores().getSurprise();

				myEmotions.addToEmotionValue(Emotion.Positive, positive*scale);
				myEmotions.addToEmotionValue(Emotion.Negative, negative*scale);
				myEmotions.addToEmotionValue(Emotion.Neutral,  neutral*scale);
				myEmotions.addToEmotionValue(Emotion.Surprised,suprise*scale);
				myEmotions.addToEmotionValue(Emotion.Angry	  ,angry*scale);
			}

			for(Emotion e: Emotion.values()){
				System.out.println(e.toString()+" "+myEmotions.getEmotionValue(e));
			}
			System.out.println("number:"+myEmotions.getNumberOfPeopleWithEmotions());
			return myEmotions;
		}
		return null;
	}
}
