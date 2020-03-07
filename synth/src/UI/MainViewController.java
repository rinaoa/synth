package UI;

import Model.Oscillator;

import Model.Synthesizer;
import Model.Waveform;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class MainViewController extends Scene {
	public final Synthesizer synth;	
	MainView mainV;
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

	public MainViewController(MainView mV) {
		super(mV, mV.getPrefWidth(), mV.getPrefHeight());
		this.mainV = mV;
		this.synth = mainV.synthesizer;
		this.keyboardView = mainV.keyboardView;
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

	private void initialize() {
		this.setOnKeyPressed(click -> {
//			System.out.println("Key Pressed: "+click.getCode());
			 switch(click.getCode()) {
			 	case Z: 	
			 		synth.setKeyFrequency(synth.C); 	
			 		play(); break;
			 	case S:		
			 		synth.setKeyFrequency(synth.CIS); 	
			 		play(); break;
			 	case X:		
			 		synth.setKeyFrequency(synth.D); 	
			 		play(); break;
			 	case D: 	
			 		synth.setKeyFrequency(synth.DIS); 	
			 		play(); break;
			 	case C:		
			 		synth.setKeyFrequency(synth.E); 	
			 		play(); break;
			 	case V:		
			 		synth.setKeyFrequency(synth.F); 	
			 		play(); break;
			 	case G:		
			 		synth.setKeyFrequency(synth.FIS); 	
			 		play(); break;
			 	case B: 	
			 		synth.setKeyFrequency(synth.G); 	
			 		play(); break;
			 	case H:		
			 		synth.setKeyFrequency(synth.GIS); 	
			 		play(); break;
			 	case N:		
			 		synth.setKeyFrequency(synth.A); 	
			 		play(); break;
			 	case J:		
			 		synth.setKeyFrequency(synth.AIS); 	
			 		play(); break;
			 	case M:		
			 		synth.setKeyFrequency(synth.H); 	
			 		play(); break;
			 	case COMMA:	
			 		synth.setKeyFrequency(synth.C2); 	
			 		play(); break;
			 		
			 	case W:
			 		synth.setKeyFrequency(synth.C2); 	
			 		play(); break;
			 	case DIGIT3:
			 		synth.setKeyFrequency(synth.CIS2); 	
			 		play(); break;
			 	case E:
			 		synth.setKeyFrequency(synth.D2); 	
			 		play(); break;
			 	case DIGIT4:
			 		synth.setKeyFrequency(synth.DIS2); 	
			 		play(); break;
			 	case R:
			 		synth.setKeyFrequency(synth.E2); 	
			 		play(); break;
			 	case T:
			 		synth.setKeyFrequency(synth.F2); 	
			 		play(); break;
			 	case DIGIT6:
			 		synth.setKeyFrequency(synth.FIS2); 	
			 		play(); break;
			 	case Y:
			 		synth.setKeyFrequency(synth.G2); 	
			 		play(); break;
			 	case DIGIT7:
			 		synth.setKeyFrequency(synth.GIS2); 	
			 		play(); break;
			 	case U:
			 		synth.setKeyFrequency(synth.A2); 	
			 		play(); break;
			 	case DIGIT8:
			 		synth.setKeyFrequency(synth.AIS2); 	
			 		play(); break;
			 	case I:
			 		synth.setKeyFrequency(synth.H2); 	
			 		play(); break;
			 	case O:
			 		synth.setKeyFrequency(synth.C3); 	
			 		play(); break;
			default:
//				play();
			}
			 
//			Platform.runLater(new Runnable() {
//
//				@Override
//				public void run() {
//					mainV.wavVC.getWavView().drawWave();
//				}
//				
//			}); 
			
		});
		
		this.setOnKeyReleased(release -> {
			synth.shouldGenerate = false;
			synth.thread.running = false;
//			System.out.println("key released");
		});
		
		btn1.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.setKeyFrequency(synth.C); 	
				 		play();
					}              
			});
			btn1.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// Cis
			//
			btn2.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.CIS); 	
					 		play();
						}              
				});
			btn2.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// D
			//
			btn3.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.D); 	
					 		play();
						}              
				});
			btn3.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// Dis
			//
			btn4.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.DIS); 	
					 		play();
						}              
				});
			btn4.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// E
			//
			btn5.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.E); 	
					 		play();
						}              
				});
			btn5.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// F
			//
			btn6.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.F); 	
					 		play();
						}              
				});
			btn6.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// Fis
			//
			btn7.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.FIS); 	
					 		play();
						}              
				});
			btn7.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// G
			//
			btn8.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.G); 	
					 		play();
						}              
				});
			btn8.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// Gis
			//
			btn9.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.GIS); 	
					 		play();
						}              
				});
			btn9.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// A
			//
			btn10.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.A); 	
					 		play();
						}              
				});
			btn10.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;     
					}
			});
			//
			// Ais
			//
			btn11.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.AIS); 	
					 		play();
						}              
				});
			btn11.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// H
			//
			btn12.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.H); 	
					 		play();
						}              
				});
			btn12.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
			//
			// C
			//
			btn13.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							synth.setKeyFrequency(synth.C2); 	
					 		play();
						}              
				});
			btn13.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						synth.shouldGenerate = false;
						synth.thread.running = false;
					}              
			});
	}
	
	private void play() {
		if (!synth.thread.isRunning()) {
			synth.shouldGenerate = true;
			synth.thread.triggerPlayback();
		}
	}

}
