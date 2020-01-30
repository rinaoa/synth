package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.SampleManager;
import model.Synthesizer;

import views.MainViewController;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Main extends Application {
	private Synthesizer synth = new Synthesizer();
	private Scene scene;
	private Stage primaryStage;
	private SampleManager sampleManager;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.sampleManager = new SampleManager();

			MainViewController controller;
			controller = new MainViewController(this, synth, sampleManager);
			Pane root = controller.getRootView();
			this.scene = new Scene(root, 700, 500);
			
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
			
//			primaryStage.onCloseRequestProperty().addListener(WindowCloseRequestHandler -> {
//					audioThread.close();
//			});
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.setResizable(true);
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
