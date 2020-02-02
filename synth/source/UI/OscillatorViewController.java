package UI;



import Model.Oscillator;
import Model.Synthesizer;
import Model.Waveform;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;

public class OscillatorViewController {
	
	OscillatorsView oscView;
	Synthesizer synth;
	private Point mouseClickedLocation;
	MainView mainV;
	
	public OscillatorViewController(MainView mainV, Synthesizer synth) {
		this.synth = synth;
		this.mainV = mainV;
		mouseClickedLocation = new Point();
		oscView = new OscillatorsView(synth);
		initialize();
	
	}
	
	public Pane getView() {
		return oscView;
	}

	private void initialize() {
		
		attachMouseHandlers(oscView.oscVs[0]);
		attachMouseHandlers(oscView.oscVs[1]);
		attachMouseHandlers(oscView.oscVs[2]);		
		
		
	}

	private void attachMouseHandlers(OscillatorView oView) {
		oView.choicebox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Waveform>() {

			@Override
			public void changed(ObservableValue<? extends Waveform> observable, Waveform oldValue, Waveform newValue) {
				oView.osc.setWaveform(newValue);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						mainV.wavVC.getWavView().drawWave();
					}
					
				});
//				Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank-cursor");
			}
			
		});
		
		oView.toneLabel.setOnMouseClicked(click -> {
			oscView.setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});

		oView.toneLabel.setOnMouseReleased(release -> {
			oscView.setCursor(Cursor.DEFAULT);
		});
		
		oView.toneLabel.setOnMouseDragged(drag -> {
			if(mouseClickedLocation.getY() != drag.getY()) {
				boolean movingUp = mouseClickedLocation.getY() - drag.getY() > 0;
				if ( movingUp && oView.osc.toneOffset < Oscillator.TONE_OFFSET_LIMIT) {
					++oView.osc.toneOffset;
				} else if (!movingUp && oView.osc.toneOffset > -Oscillator.TONE_OFFSET_LIMIT) {
					--oView.osc.toneOffset;
				}
			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					mainV.wavVC.getWavView().drawWave();
				}
				
			});
			
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
			oView.osc.applyToneOffset();
			oView.toneLabel.setText("x "+ String.format("%.3f", (double)(oView.osc.toneOffset / 1000d)));
		});
		
		
		oView.volumeLabel.setOnMouseClicked(click -> {
			oscView.setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});

		oView.volumeLabel.setOnMouseReleased(release -> {
			oscView.setCursor(Cursor.DEFAULT);
		});
		
		oView.volumeLabel.setOnMouseDragged(drag -> {
			if(mouseClickedLocation.getY() != drag.getY()) {
				boolean movingUp = mouseClickedLocation.getY() - drag.getY() > 0;
				if ( movingUp && oView.osc.volume < Oscillator.MAX_VOLUME) {
					++oView.osc.volume;
				} else if (!movingUp && oView.osc.volume > Oscillator.MIN_VOLUME) {
					--oView.osc.volume;
				}
				
			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					mainV.wavVC.getWavView().drawWave();
				}
				
			});
			
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
//			oView.osc.applyToneOffset();
			oView.volumeLabel.setText(oView.osc.volume+"%");
//			System.out.println(oView.osc.volume);
			System.out.println((oView.osc.volume/100d));

		});
	}
}
