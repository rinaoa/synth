package UI;

import Model.Oscillator;
import Model.Synthesizer;
import Model.Waveform;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.KeyCode;

public class MainViewController extends Scene {
	
	
	
	public final Synthesizer synth;
	
	
	MainView mainV;

	public MainViewController(MainView mV) {
		super(mV, mV.getPrefWidth(), mV.getPrefHeight());
		this.mainV = mV;
		this.synth = mainV.synthesizer;
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
		
		
	}
	
	private void play() {
		if (!synth.thread.isRunning()) {
			synth.shouldGenerate = true;
			synth.thread.triggerPlayback();
		}
	}

}
