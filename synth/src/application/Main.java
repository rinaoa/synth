 package application;
	
import java.io.BufferedInputStream;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.AudioThread;
import model.Synthesizer;
import model.wave.WaveData;
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
			MainViewController controller;
			controller = new MainViewController(this, synth);
			Pane root = controller.getRootView();
			Scene scene = new Scene(root, 700, 500);
			
			scene.setOnKeyPressed((KeyEvent event) ->{
				System.out.println("Key Pressed: "+event.getCode());
				 switch(event.getCode()) {
				 	case Z:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.C);break;
				 	case S:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.CIS);break;
				 	case X:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.D);break;
				 	case D:	System.out.print("......key pressed"); controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.DIS);break;
				 	case C:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.E);break;
				 	case V:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.F);break;
				 	case G:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.FIS);break;
				 	case B:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.G);break;
				 	case H:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.GIS);break;
				 	case N:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.A);break;
				 	case J:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.AIS);break;
				 	case M:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.H);break;
				 	case K:	controller.playKey((synth.getOctave() * 0.8f)+ MainViewController.C2);break;
				 
				 }
			});
			
			scene.setOnKeyReleased((KeyEvent event) -> {
				System.out.print("......key released");
            	controller.pauseKey();
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
