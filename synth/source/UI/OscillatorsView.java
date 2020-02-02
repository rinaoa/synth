package UI;

import Model.Oscillator;
import Model.Waveform;
import Model.Synthesizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
//		this.osc2=synth.getOsc(1);
//		this.osc3=synth.getOsc(2);
//		new OscillatorView(osc2),new OscillatorView(osc3)};
//		oscV1 = new OscillatorView(osc1);
//		oscV2 = new OscillatorView(osc2);
//		oscV3 = new OscillatorView(osc3);
	}

	private void initialize() {
			
	}

}
