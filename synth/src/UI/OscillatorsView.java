package UI;

import Model.Oscillator;

import Model.Synthesizer;
import javafx.scene.layout.VBox;

public class OscillatorsView extends VBox {
	
	Oscillator osc1, osc2, osc3;
	OscillatorView oscVs[]=new OscillatorView[3];
	Synthesizer synth;
	
	public OscillatorsView(Synthesizer synthesizer) {
		this.synth = synthesizer;
		for (int i=0;i<oscVs.length;i++) {
			oscVs[i]= new OscillatorView(synth.getOsc(i));
			getChildren().add(oscVs[i]);

		}
	}

	private void initialize() {
			
	}

}
