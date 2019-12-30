package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingsView extends VBox{

	Label lbl = new Label("Settings");
	
	public SettingsView() {
    	this.setBackground(new Background(new BackgroundFill(Color.rgb(12, 82, 10, 0.3), CornerRadii.EMPTY, Insets.EMPTY)));

    	getChildren().add(lbl);
    	
		this.setWidth(200);
		this.setHeight(500);
	}
}
