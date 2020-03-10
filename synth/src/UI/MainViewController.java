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
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light.Point;
import javafx.scene.effect.Lighting;
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
				case UP:
					System.out.println(synth.modulation);

					if(synth.modulation < synth.MAX_MOD) {
						synth.modulation++; 
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								mainV.wavVC.getWavView().drawWave();
							}
						});
					}
				case DOWN:
					if(synth.modulation > synth.MIN_MOD) {
						
						synth.modulation--; 
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								mainV.wavVC.getWavView().drawWave();
							}
						});					
					}
				case Z: 	
			 		play(synth.C); break;
			 	case S:		
			 		play(synth.CIS); break;
			 	case X:		
			 		play(synth.D); break;
			 	case D: 	
			 		play(synth.DIS); break;
			 	case C:		
			 		play(synth.E); break;
			 	case V:		
			 		play(synth.F); break;
			 	case G:		
			 		play(synth.FIS); break;
			 	case B: 	
			 		play(synth.G); break;
			 	case H:		
			 		play(synth.GIS); break;
			 	case N:		
			 		play(synth.A); break;
			 	case J:		
			 		play(synth.AIS); break;
			 	case M:		
			 		play(synth.H); break;
			 	case COMMA:	
			 		play(synth.C2); break;
			 		
			 	case W:
			 		play(synth.C2); break;
			 	case DIGIT3:
			 		play(synth.CIS2); break;
			 	case E:
			 		play(synth.D2); break;
			 	case DIGIT4:
			 		play(synth.DIS2); break;
			 	case R:
			 		play(synth.E2); break;
			 	case T:
			 		play(synth.F2); break;
			 	case DIGIT6:
			 		play(synth.FIS2); break;
			 	case Y:
			 		play(synth.G2); break;
			 	case DIGIT7:
			 		play(synth.GIS2); break;
			 	case U:
			 		play(synth.A2); break;
			 	case DIGIT8:
			 		play(synth.AIS2); break;
			 	case I:
			 		play(synth.H2); break;
			 	case O:
			 		play(synth.C3); break;
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
			pause();
//			System.out.println("key released");
		});
		
		btn1.addEventFilter(MouseEvent.MOUSE_PRESSED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
				 		play(synth.C);
					}              
			});
			btn1.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// Cis
			//
			btn2.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.CIS);
						}              
				});
			btn2.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// D
			//
			btn3.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.D);
						}              
				});
			btn3.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// Dis
			//
			btn4.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.DIS);
						}              
				});
			btn4.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// E
			//
			btn5.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.E);
						}              
				});
			btn5.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// F
			//
			btn6.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.F);
						}              
				});
			btn6.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// Fis
			//
			btn7.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.FIS);
						}              
				});
			btn7.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// G
			//
			btn8.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.G);
						}              
				});
			btn8.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// Gis
			//
			btn9.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.GIS);
						}              
				});
			btn9.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// A
			//
			btn10.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.A);
						}              
				});
			btn10.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();   
					}
			});
			//
			// Ais
			//
			btn11.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.AIS);
						}              
				});
			btn11.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// H
			//
			btn12.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.H);
						}              
				});
			btn12.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
			//
			// C
			//
			btn13.addEventFilter(MouseEvent.MOUSE_PRESSED,                
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
					 		play(synth.C2);
						}              
				});
			btn13.addEventFilter(MouseEvent.MOUSE_RELEASED,                
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						pause();
					}              
			});
	}
	
	private void pause() {
		synth.shouldGenerate = false;
		synth.thread.running = false;
		btn1.setEffect(null);
		btn2.setEffect(null);
		btn3.setEffect(null);
		btn4.setEffect(null);
		btn5.setEffect(null);
		btn6.setEffect(null);
		btn7.setEffect(null);
		btn8.setEffect(null);
		btn9.setEffect(null);
		btn10.setEffect(null);
		btn11.setEffect(null);
		btn12.setEffect(null);
	}
	
	private void play(double key) {
		if (!synth.thread.isRunning()) {
			synth.setKeyFrequency(key); 	
			synth.shouldGenerate = true;
			synth.thread.triggerPlayback();
		}
		
		if (key == synth.C || key == synth.C2) {
			btn1.setEffect(new Lighting());
		} else if (key == synth.CIS || key == synth.CIS2) {
			btn2.setEffect(new BoxBlur());
		} else if (key == synth.D || key == synth.D2) {
			btn3.setEffect(new Lighting());
		} else if (key == synth.DIS || key == synth.DIS2) {
			btn4.setEffect(new BoxBlur());
		} else if (key == synth.E || key == synth.E2) {
			btn5.setEffect(new Lighting());
		} else if (key == synth.F || key == synth.F2) {
			btn6.setEffect(new Lighting());
		} else if (key == synth.FIS || key == synth.FIS2) {
			btn7.setEffect(new BoxBlur());
		} else if (key == synth.G || key == synth.G2) {
			btn8.setEffect(new Lighting());
		} else if (key == synth.GIS || key == synth.GIS2) {
			btn9.setEffect(new BoxBlur());
		} else if (key == synth.A || key == synth.A2) {
			btn10.setEffect(new Lighting());
		} else if (key == synth.AIS || key == synth.AIS2) {
			btn11.setEffect(new BoxBlur());
		} else if (key == synth.H || key == synth.H2) {
			btn12.setEffect(new Lighting());
		}
	}

}
