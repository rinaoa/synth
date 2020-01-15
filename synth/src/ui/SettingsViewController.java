package ui;

import application.Main;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import model.Sample;
import model.Synthesizer;
import views.MainView;
import views.ViewController;

public class SettingsViewController extends ViewController<Main>{
	Synthesizer synth;
	ListView<Sample> samplesView;
	ObservableList<Sample> observableList;
	
	public SettingsViewController(Main application, Synthesizer s) {
		super(application);
		this.synth = s;
		
		rootView = new SettingsView(synth);
		SettingsView view = (SettingsView) rootView;
		
		this.samplesView = view.samplesView;
		this.observableList = view.observableList;
		initialize();
	}
	
	@Override
	public void initialize() {
		initializeListeners();
	}
	
	private void initializeListeners(){
		System.out.println("////////////////");
		samplesView.setOnDragDetected(new EventHandler<MouseEvent>() {
	       @Override
	       public void handle(MouseEvent event){
	          System.out.println("setOnDragDetected");
	       
	          Dragboard dragBoard = samplesView.startDragAndDrop(TransferMode.COPY);
	          ClipboardContent content = new ClipboardContent();
	          content.putString(samplesView.getSelectionModel().getSelectedItem().getName());
	          dragBoard.setContent(content);
	       }
	   });
	 
		samplesView.setOnDragDone(new EventHandler<DragEvent>(){
	       @Override
	       public void handle(DragEvent dragEvent){
	    	   System.out.println("setOnDragDone");
	       }
       });
	 }
}
