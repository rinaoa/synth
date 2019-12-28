package views;

import application.Main;

public class MainViewController extends ViewController<Main>{

	public MainViewController(Main application) {
		super(application);
		
		rootView = new MainView();
		MainView view = (MainView) rootView;
		
		initialize();
	}
	
	@Override
	public void initialize() {
		
	}

}
