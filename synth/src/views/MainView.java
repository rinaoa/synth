package views;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.AudioThread;
import model.Synthesizer;

public class MainView extends AnchorPane {
	Label lbl;
	
	public MainView(){
		lbl = new Label("Label");
		getChildren().add(lbl);
		
	}
	
//	in the class Synthesizer
//	public static class AudioInfo{
//		public static final int SAMPLE_RATE = 44100; 
//	}
}
