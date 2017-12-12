package com.hlab.lib.common.controller;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.hlab.lib.model.ConfigFile;

public class WebCamController {
	private static WebCamController instance = null;
	private String visionfolderpath = null;
	private Webcam webcam;
	
	protected WebCamController(){
		try{
			ConfigFile properties = new ConfigFile();
			visionfolderpath = properties.getFileVisionFolderPath();
			webcam = Webcam.getDefault();
			webcam.open();
		}catch (Exception e) {
			
		}
	}
	
	public static WebCamController getInstance(){
		if(instance == null){
			instance = new WebCamController();
		}
		return instance;
	}
	
	public File getImageSeenByCamera(){
		File img = new File(visionfolderpath+"temp.png");
		try {
			ImageIO.write(webcam.getImage(), "PNG",img );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
}
