package application;
	
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.javafx.stage.WindowCloseRequestHandler;

import javafx.application.Application;
import javafx.stage.Stage;
import model.AudioThread;
import model.Synthesizer;
import views.MainViewController;
import views.ViewController;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Main extends Application {
	private Scene scene;
	private Stage primaryStage;
	
	private boolean shouldGenerate;
	private int wavePos;
	
	private final AudioThread audioThread = new AudioThread(() -> {
		if(!shouldGenerate) {
			return null;
		}
		short s [] = new short[AudioThread.BUFFER_SIZE];
		for(int i = 0; i < AudioThread.BUFFER_SIZE; i++) {
			s[i] = (short)(Short.MAX_VALUE * Math.sin((2*Math.PI * 440) / Synthesizer.AudioInfo.SAMPLE_RATE * wavePos++)); 
		}
		return s; //return buffer
	});
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			
			ViewController<Main> controller;
			controller = new MainViewController(this);
			Pane root = controller.getRootView();
			Scene scene = new Scene(root,800,600);
			
			scene.setOnKeyPressed((KeyEvent event) -> {
				KeyCode kCode= event.getCode();
				System.out.println("Key Pressed"+kCode.getName());
	            if(!audioThread.isRunning()) {
	            	System.out.println("-----");
	            	shouldGenerate = true;
	            	audioThread.triggerPlayback();
	            }
	        });
			
			scene.setOnKeyReleased((KeyEvent event) -> {
            	shouldGenerate = false;
	        });
			
//			primaryStage.onCloseRequestProperty().addListener(WindowCloseRequestHandler -> {
//					audioThread.close();
//			});
			
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
		audioThread.stop();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
