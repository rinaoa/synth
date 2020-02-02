package UI;

import Model.Oscillator;
import Model.Synthesizer;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.VBox;

public class SettingsController extends VBox {
	
	Button octavePlus, octaveMinus;
	Label pitchText, pitchValue;
	Synthesizer synth;
	private Point mouseClickedLocation = new Point();
	public SettingsController(Synthesizer synth) {
		this.synth = synth;
		octavePlus = new Button("Oktave +");
		octaveMinus = new Button("Oktave -");
		
		pitchText = new Label("Pitch:");
		pitchValue = new Label("x0,00");
		
		octaveMinus.setPadding(new Insets(10));
		octavePlus.setPadding(new Insets(10));
		initialize();
		getChildren().addAll(octavePlus,octaveMinus, pitchText, pitchValue);
	}

	private void initialize() {
		octavePlus.setOnMouseClicked(click -> {
			if (synth.getOctave() < Synthesizer.MAX_OKTAVE-1) {
				synth.setOctave(synth.getOctave() +1);
			}
		});
		
		octaveMinus.setOnMouseClicked(click -> {
			if (synth.getOctave() > Synthesizer.MIN_OKTAVE) {
				synth.setOctave(synth.getOctave() -1);
			}
		});
		
		pitchValue.setOnMouseClicked(click -> {
			setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});

		pitchValue.setOnMouseReleased(release -> {
			setCursor(Cursor.DEFAULT);
		});
		
		pitchValue.setOnMouseDragged(drag -> {
			if(mouseClickedLocation.getY() != drag.getY()) {
				boolean movingUp = mouseClickedLocation.getY() - drag.getY() > 0;
				if ( movingUp && synth.pitch < Synthesizer.MAX_PITCH-10) {
					synth.pitch +=10;
				} else if (!movingUp && synth.pitch > -Synthesizer.MAX_PITCH+10) {
					synth.pitch-=10;
				}
			}
			
			
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
//			oView.osc.applyToneOffset();
			pitchValue.setText("x "+synth.pitch/10);
//			System.out.println(oView.osc.volume);
//			System.out.println(synth.pitch/1000f);

		});
	}
	
	

}
