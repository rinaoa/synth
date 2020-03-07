 package UI;

import Model.Synthesizer;
import javafx.scene.layout.AnchorPane;

public class MainView extends AnchorPane {
	
	OscillatorViewController oscVC;
	Synthesizer synthesizer;

	SettingsController settingsC;
	WaveViewController wavVC;
	KeyboardView keyboardView;
	
	public MainView(Synthesizer synth) {
		this.synthesizer = synth;
		this.keyboardView = new KeyboardView();
		setPrefSize(745, 500);
		
		wavVC = new WaveViewController(synth);
		oscVC = new OscillatorViewController(this, synth);
		settingsC = new SettingsController(this, synth);
		
		getChildren().add(oscVC.getView());
		AnchorPane.setLeftAnchor(oscVC.getView(), 497.0);
		
		getChildren().add(wavVC.getWavView());
		
		getChildren().add(keyboardView);
        AnchorPane.setTopAnchor(keyboardView, 300.0);
        
        getChildren().add(settingsC);
        AnchorPane.setTopAnchor(settingsC, 300.0);
        AnchorPane.setLeftAnchor(settingsC, 497.0);
	}

}
