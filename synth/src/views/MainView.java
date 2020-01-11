package views;

import application.Main;
import javafx.scene.layout.AnchorPane;
import model.Synthesizer;
import ui.*;

public class MainView extends AnchorPane {
	WaveView waveView;
	KeyboardView keyboardView;
	SettingsView settingsView;
	
	public MainView(Synthesizer s, Main application){
		
		this.waveView = new WaveView();
		this.keyboardView = new KeyboardView();
		
		ViewController<Main> controller = new SettingsViewController(application, s);
		this.settingsView = (SettingsView) controller.getRootView();
		
		getChildren().add(waveView);
		
		getChildren().add(keyboardView);
        AnchorPane.setTopAnchor(keyboardView, waveView.getHeight());
        
        getChildren().add(settingsView);
        AnchorPane.setLeftAnchor(settingsView, 500.0);
	}
}
