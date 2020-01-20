package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class MenuBox extends VBox {
	
	public Label aktWav;
	public Button export;
	public Button volumeBt;
	public Slider volumeSl;
	
	public MenuBox() {
		aktWav=new Label("aktWav.wav");
		export =new Button("Export");
		volumeBt=new Button("Volume");
		volumeSl=new Slider();
		this.getChildren().addAll(aktWav,export,volumeBt,volumeSl);
	}

}
