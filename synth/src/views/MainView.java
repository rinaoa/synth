package views;

import javafx.scene.layout.AnchorPane;
import model.Synthesizer;
import ui.*;

public class MainView extends AnchorPane {
	WaveView waveView;
	KeyboardView keyboardView;
	SettingsView settingsView;
	
	public MainView(Synthesizer s){
		
		this.waveView = new WaveView();
		this.keyboardView = new KeyboardView();
		this.settingsView = new SettingsView(s);
		
		getChildren().add(waveView);
		
		getChildren().add(keyboardView);
        AnchorPane.setTopAnchor(keyboardView, waveView.getHeight());
        
        getChildren().add(settingsView);
        AnchorPane.setLeftAnchor(settingsView, 500.0);
	}
}
