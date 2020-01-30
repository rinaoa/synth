package views;

import java.io.BufferedInputStream;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import application.Main;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import model.AudioThread;
import model.Sample;
import model.SampleManager;
import model.Synthesizer;
import ui.*;

public class MainViewController extends ViewController<Main>{
	Synthesizer synth;
	
	Sample currentSample; //use Sample instead
	private AudioThread audioThread1;
	private boolean keyPressed;
	private AudioThread audioThread2;
	private boolean shouldGenerate2;
	private SampleManager sampleManager;
	
	public final static float C = 0.5f;
	public final static float CIS = 0.55f;
	public final static float D = 0.6f;
	public final static float DIS = 0.65f;
	public final static float E = 0.7f;
	public final static float F = 0.75f;
	public final static float FIS = 0.8f;
	public final static float G = 0.85f;
	public final static float GIS = 0.9f;
	public final static float A = 0.95f;
	public final static float AIS = 1.0f;
	public final static float H = 1.05f;
	public final static float C2 = 1.1f;
	
	KeyboardView keyboardView;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	Button btn10;
	Button btn11;
	Button btn12;
	Button btn13;
	
	WaveView waveView;
	
	public MainViewController(Main application, Synthesizer s, SampleManager sm) {
		super(application);
		this.synth = s;
		this.sampleManager = sm;
		rootView = new MainView(synth, application);
		MainView view = (MainView) rootView;
		
		waveView = view.waveView;
		
		keyboardView = view.keyboardView;
		btn1 = keyboardView.btn1;
		btn2 = keyboardView.btn2;
		btn3 = keyboardView.btn3;
		btn4 = keyboardView.btn4;
		btn5 = keyboardView.btn5;
		btn6 = keyboardView.btn6;
		btn7 = keyboardView.btn7;
		btn8 = keyboardView.btn8;
		btn9 = keyboardView.btn9;
		btn10 = keyboardView.btn10;
		btn11 = keyboardView.btn11;
		btn12 = keyboardView.btn12;
		btn13 = keyboardView.btn13;
		
		currentSample = synth.currentSample.getValue();
		initialize();
	}
	
	private void initializeStreams() {
		audioThread1 = new AudioThread(() ->{
			if(!keyPressed) {
				return null;
			}
			System.out.print("::::::::::::::::");
			return currentSample;
		}, waveView.gc);
	}
	
	public void playKey(float d) {
		audioThread1.setPitch(d);
		System.out.println("Key Pressed" + audioThread1.isNotKilled());
        if(audioThread1.isNotKilled()) {
        	System.out.println("-----");
        	keyPressed = true;
        	audioThread1.triggerPlayback();
        }
	}
	
	public void pauseKey() {
		System.out.println("Key Released" + audioThread1.isNotKilled());
		keyPressed = false;
		audioThread1.pause();
	}
	
	private void initializeListeners(){
		System.out.println("\n////......./////////\n");
		
		keyboardView.setOnDragEntered(new EventHandler<DragEvent>(){
			 @Override
		     public void handle(DragEvent dragEvent){
			     System.out.println("setOnDragEntered");
			     
			     keyboardView.setBlendMode(BlendMode.OVERLAY);
			 }
		 });
		 
		 keyboardView.setOnDragOver(new EventHandler<DragEvent>(){
		     @Override
		     public void handle(DragEvent dragEvent){
			     System.out.println("setOnDragOver");
			     
			     dragEvent.acceptTransferModes(TransferMode.ANY);
		     }
	     });
		  
		 keyboardView.setOnDragExited(new EventHandler<DragEvent>(){
		     @Override
		     public void handle(DragEvent dragEvent){
			     System.out.println("setOnDragExited");
		     
			     keyboardView.setBlendMode(null);
		     }
		 });
		 keyboardView.setOnDragDropped(new EventHandler<DragEvent>(){
		     @Override
		     public void handle(DragEvent dragEvent){
			     try {
			    	 System.out.print("Drag dropped");
		            Dragboard db = dragEvent.getDragboard();
		            boolean success = false;
		 
		            if (db.hasString()) {	 
		                System.out.print(db.getString());
		                System.out.print(sampleManager.samples.keySet());
		                synth.setSample(sampleManager.samples.get(db.getString()));
		                success = true;
		            }
		            dragEvent.setDropCompleted(success);
		 
		            dragEvent.consume();
			     } catch (Exception e) {
			    	 dragEvent.setDropCompleted(false);
			    	 e.printStackTrace(); 
		    	 } 
			     finally {
			    	 dragEvent.consume(); 
		    	 }
		     }
	     });
	 }
	@Override
	public void initialize() {
		initializeStreams();
		initializeListeners();
		//
		// C
		//
		
		btn1.addEventFilter(MouseEvent.MOUSE_PRESSED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					playKey((synth.getOctave() * 0.8f)+ C);
				}              
		});
		btn1.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// Cis
		//
		btn2.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ CIS);
					}              
			});
		btn2.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// D
		//
		btn3.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ D);
					}              
			});
		btn3.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// Dis
		//
		btn4.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ DIS);
					}              
			});
		btn4.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// E
		//
		btn5.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ E);
					}              
			});
		btn5.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// F
		//
		btn6.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ F);
					}              
			});
		btn6.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// Fis
		//
		btn7.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ FIS);
					}              
			});
		btn7.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// G
		//
		btn8.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ G);
					}              
			});
		btn8.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// Gis
		//
		btn9.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ GIS);
					}              
			});
		btn9.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// A
		//
		btn10.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ A);
					}              
			});
		btn10.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();     
				}
		});
		//
		// Ais
		//
		btn11.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ AIS);
					}              
			});
		btn11.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// H
		//
		btn12.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ H);
					}              
			});
		btn12.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		//
		// C
		//
		btn13.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						playKey((synth.getOctave() * 0.8f)+ C2);
					}              
			});
		btn13.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					pauseKey();
				}              
		});
		
		synth.currentSampleProperty().addListener(new ChangeListener<Sample>() {
			@Override
			public void changed(ObservableValue<? extends Sample> observable, Sample oldValue, Sample newValue) {
				Platform.runLater(() ->{
					try {
						currentSample = newValue;
					} 
					
					catch (Exception e) { e.printStackTrace(); }
				});
			}
		});
	}

}
