package views;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import application.Main;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import model.AudioThread;
import model.Synthesizer;
import model.wave.WaveData;
import ui.*;

public class MainViewController extends ViewController<Main>{
	Synthesizer synth;
	
	WaveData waveFile;
	private AudioThread audioThread1;
	private boolean keyPressed;
	private AudioThread audioThread2;
	private boolean shouldGenerate2;
	
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
	
	public MainViewController(Main application, Synthesizer s) {
		super(application);
		
		this.synth = s;
		rootView = new MainView(synth, application);
		MainView view = (MainView) rootView;
		
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
		
		initialize();
	}
	
	private void initializeStreams() {
		try {
			waveFile = WaveData.create(new BufferedInputStream( new FileInputStream("assets/bounce.wav")));
			System.out.print(waveFile.data);
			audioThread1 = new AudioThread(() ->{
				if(!keyPressed) {
					return null;
				}
				System.out.print("::::::::::::::::");
				return waveFile;
			});
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	private void initializeListeners(){
	System.out.println("\n////......./////////\n");
	
	//+
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
		     
		     dragEvent.acceptTransferModes(TransferMode.COPY);
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
		     System.out.println("setOnDragDropped");
		     
		     dragEvent.setDropCompleted(true);
	     }
	     });
	 }
	@Override
	public void initialize() {
		initializeStreams();
		initializeListeners();
		//
		// A
		//
		btn1.addEventFilter(MouseEvent.MOUSE_PRESSED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.5f);
					System.out.println("Key Pressed" + audioThread1.isNotKilled());
		            if(audioThread1.isNotKilled()) {
		            	System.out.println("-----");
		            	keyPressed = true;
		            	audioThread1.triggerPlayback();
		            }
				}              
		});
		btn1.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// B
		//
		btn2.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.55f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn2.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// C
		//
		btn3.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.6f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn3.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// D
		//
		btn4.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.65f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn4.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// E
		//
		btn5.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.7f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn5.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// F
		//
		btn6.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.8f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn6.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// G
		//
		btn7.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.85f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn7.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// H
		//
		btn8.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.9f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn8.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// I
		//
		btn9.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 0.95f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn9.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// J
		//
		btn10.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 1.0f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn10.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// K
		//
		btn11.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 1.05f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn11.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// L
		//
		btn12.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 1.1f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn12.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		//
		// M
		//
		btn13.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						audioThread1.setPitch((synth.getOctave() * 0.8f)+ 1.2f);
						System.out.println("Key Pressed" + audioThread1.isNotKilled());
			            if(audioThread1.isNotKilled()) {
			            	System.out.println("-----");
			            	keyPressed = true;
			            	audioThread1.triggerPlayback();
			            }
					}              
			});
		btn13.addEventFilter(MouseEvent.MOUSE_RELEASED,                
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Key Released" + audioThread1.isNotKilled());
					keyPressed = false;
					audioThread1.pause();
				}              
		});
		
	}

}
