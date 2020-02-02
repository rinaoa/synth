package Main;

import Model.Synthesizer;
import UI.MainView;
import UI.MainViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public Synthesizer synth;
	
	Stage pStage;
	
	MainViewController mainVC;
	MainView mainV;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		synth = new Synthesizer();
		
		pStage = primaryStage;
		mainV = new MainView(synth);
		mainVC = new MainViewController(mainV);
		
		pStage.setOnCloseRequest(close -> {
			mainVC.synth.thread.close();
		});
		
		pStage.setScene(mainVC);
		pStage.setResizable(false);
		pStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
