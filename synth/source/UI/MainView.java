 package UI;

import Model.Synthesizer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainView extends AnchorPane {
	
	OscillatorViewController oscVC;
	Synthesizer synthesizer;

	SettingsController settingsC;
	WaveViewController wavVC;
	
	public MainView(Synthesizer synth) {
		this.synthesizer = synth;
		setPrefSize(800, 500);
		
		wavVC = new WaveViewController(synth);
		oscVC = new OscillatorViewController(this, synth);
		settingsC = new SettingsController(synth);
//		System.out.println(oscVC.getView().getPrefWidth());
		AnchorPane.setLeftAnchor(settingsC, 500d);
		AnchorPane.setLeftAnchor(wavVC.getWavView(), 250d);
		AnchorPane.setTopAnchor(wavVC.getWavView(), 200d);
		
		getChildren().add(oscVC.getView());
		getChildren().add(settingsC);
		getChildren().add(wavVC.getWavView());
	}

}
