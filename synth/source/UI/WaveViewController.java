package UI;

import Model.Synthesizer;

public class WaveViewController {
	
	private WaveView wavView;
	
	public WaveViewController(Synthesizer synth){
		this.wavView = new WaveView(synth.getOscillators());
	}
	
	public WaveView getWavView() {
		return wavView;
	}

}
