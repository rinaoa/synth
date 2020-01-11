 package application;
	
import java.io.BufferedInputStream;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


import javafx.application.Application;
import javafx.stage.Stage;
import model.AudioThread;
import model.Synthesizer;
import model.wave.WaveData;
import ui.SettingsView;
import ui.SettingsViewController;
import views.MainViewController;
import views.ViewController;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Main extends Application {
	private Synthesizer synth = new Synthesizer();
	private Scene scene;
	private Stage primaryStage;
	
	private boolean shouldGenerate;
	private int wavePos;
	
//	private final AudioThread audioThread = new AudioThread(() -> {
//		if(!shouldGenerate) {
//			return null;
//		}
//		short s [] = new short[AudioThread.BUFFER_SIZE];
//		for(int i = 0; i < AudioThread.BUFFER_SIZE; i++) {
//			s[i] = (short)(Short.MAX_VALUE * Math.sin((2*Math.PI * 440) / Synthesizer.AudioInfo.SAMPLE_RATE * wavePos++)); 
//		}
//		return s; //return buffer
//	});
//	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			
			//WaveData data = WaveData.create(new BufferedInputStream( new FileInputStream("assets/Cello_A2.wav" ) ));
			//int dataInt = data.format;
//			if(data == null) {
//				System.out.print("JJJJJJJJJJJJJJJJ");
//			}
			ViewController<Main> controller;
			controller = new MainViewController(this, synth);
			Pane root = controller.getRootView();
			Scene scene = new Scene(root, 700, 500);
			
			scene.setOnKeyPressed((KeyEvent event) -> {
//	            System.out.println("Key Pressed");
//	            if(!audioThread.isRunning()) {
//	            	System.out.println("-----");
//	            	shouldGenerate = true;
//	            	audioThread.triggerPlayback();
//	            }
	        });
			
			scene.setOnKeyReleased((KeyEvent event) -> {
            	shouldGenerate = false;
	        });
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public void stop() {
		//audioThread.stop();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
