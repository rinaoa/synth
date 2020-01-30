package ui;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class KeyboardView extends AnchorPane{
	public Button btn1 = new Button("");
	public Button btn2 = new Button("");
	public Button btn3 = new Button("");
	public Button btn4 = new Button("");
	public Button btn5 = new Button("");
	public Button btn6 = new Button("");
	public Button btn7 = new Button("");
	public Button btn8 = new Button("");
	public Button btn9 = new Button("");
	public Button btn10 = new Button("");
	public Button btn11 = new Button("");
	public Button btn12 = new Button("");
	public Button btn13 = new Button("");
	
	public KeyboardView() {		
//		SVGPath buttonSVG=new SVGPath();
//		buttonSVG.setContent(getPathFromSVG("linksW"));
//		buttonSVG.setFill(Color.web("#7395AE"));
//		btn1.setGraphic(buttonSVG);
		
		btn1.setId("a-icon"); 
		btn1.getStyleClass().add("icon-button");
		btn2.setId("b-icon"); 
		btn2.getStyleClass().add("icon-button");
		btn3.setId("c-icon"); 
		btn3.getStyleClass().add("icon-button");
		btn4.setId("b-icon"); 
		btn4.getStyleClass().add("icon-button");
		btn5.setId("d-icon"); 
		btn5.getStyleClass().add("icon-button");
		btn6.setId("a-icon"); 
		btn6.getStyleClass().add("icon-button");
		btn7.setId("b-icon"); 
		btn7.getStyleClass().add("icon-button");
		btn8.setId("c-icon"); 
		btn8.getStyleClass().add("icon-button");
		btn9.setId("b-icon"); 
		btn9.getStyleClass().add("icon-button");
		btn10.setId("c-icon"); 
		btn10.getStyleClass().add("icon-button");
		btn11.setId("b-icon"); 
		btn11.getStyleClass().add("icon-button");
		btn12.setId("d-icon"); 
		btn12.getStyleClass().add("icon-button");
		btn13.setId("e-icon"); 
		btn13.getStyleClass().add("icon-button");

		getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13);
		
		AnchorPane.setLeftAnchor(btn2, 35.0); 
		AnchorPane.setLeftAnchor(btn3, 71.0); 
		AnchorPane.setLeftAnchor(btn4, 106.0); 
		AnchorPane.setLeftAnchor(btn5, 142.0); 
		AnchorPane.setLeftAnchor(btn6, 213.0); 
		AnchorPane.setLeftAnchor(btn7, 248.0); 
		AnchorPane.setLeftAnchor(btn8, 284.0); 
		AnchorPane.setLeftAnchor(btn9, 319.0); 
		AnchorPane.setLeftAnchor(btn10, 355.0); 
		AnchorPane.setLeftAnchor(btn11, 390.0); 
		AnchorPane.setLeftAnchor(btn12, 426.0); 

		this.setWidth(497);
		this.setHeight(200);
	}
}