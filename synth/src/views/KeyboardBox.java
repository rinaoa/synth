package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class KeyboardBox {
	
	public KeyboardBox() throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("Keyboard.fxml"));
	}
}
