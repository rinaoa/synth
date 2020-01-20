package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WaveView extends AnchorPane{
	Label lbl;

	public WaveView() {
    	this.setBackground(new Background(new BackgroundFill(Color.rgb(52, 82, 101, 0.3), CornerRadii.EMPTY, Insets.EMPTY)));
    	
		Rectangle r = new Rectangle();
		r.setX(0);
		r.setY(0);
		r.setWidth(500);
		r.setHeight(300);
		r.setFill(Color.rgb(52, 82, 101, 0.3));
		
		lbl = new Label("WaveView");
		getChildren().addAll(r, lbl);
		
		setWidth(497);
		setHeight(300);
	}

}
